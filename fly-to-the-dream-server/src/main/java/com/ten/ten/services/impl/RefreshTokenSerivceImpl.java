package com.ten.ten.services.impl;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.CodeResponseConstant;
import com.ten.ten.constants.MessageResponseConstant;
import com.ten.ten.dtos.RefreshTokenDto;
import com.ten.ten.dtos.TokenRefreshResponse;
import com.ten.ten.entities.RefreshTokenEntity;
import com.ten.ten.exceptions.TokenRefreshException;
import com.ten.ten.repositories.AccessTokenRepository;
import com.ten.ten.repositories.RefreshTokenRepository;
import com.ten.ten.repositories.UserReporitory;
import com.ten.ten.security.CustomUserDetails;
import com.ten.ten.security.JwtTokenProvider;
import com.ten.ten.services.AccessTokenService;
import com.ten.ten.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenSerivceImpl implements RefreshTokenService {

    @Value("${app-jwt-refresh-expiration-milliseconds}")
    private Long JWT_REFRESH_EXPIRATION;

    @Autowired
    private UserReporitory userReporitory;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private ResponseResult responseResult;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AccessTokenService accessTokenService;
    @Override
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            return null;
        }
        return token;
    }

    @Override
    public RefreshTokenEntity createRefreshToken(String username) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(userReporitory.findByUsername(username));
        refreshToken.setExpiryDate(Instant.now().plusMillis(JWT_REFRESH_EXPIRATION));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(String id) {
        return refreshTokenRepository.deleteByUser(userReporitory.findById(id));
    }


    public ResponseEntity<ResponseResult> refreshToken(RefreshTokenDto refreshTokenDto) {
        try {
            String requestRefreshToken = refreshTokenDto.getRefreshToken();
            RefreshTokenEntity token = refreshTokenRepository.findByToken(requestRefreshToken);
            if(Objects.isNull(token)) {
                return responseResult.ResponseResult(
                        HttpStatus.NOT_FOUND,
                        CodeResponseConstant.AUTH.NOT_FOUND_REFRESH_TOKEN,
                        null,
                        MessageResponseConstant.AUTH.NOT_FOUND_REFRESH_TOKEN
                );
            }
            RefreshTokenEntity verifyToken = verifyExpiration(token);
            if(Objects.isNull(verifyToken)) {
                return responseResult.ResponseResult(
                        HttpStatus.FORBIDDEN,
                        CodeResponseConstant.AUTH.EXPIRED_REFRESH_TOKEN,
                        null,
                        MessageResponseConstant.AUTH.EXPIRED_REFRESH_TOKEN
                );
            }
            String newToken = jwtTokenProvider.generateToken(token.getUser().getUsername());
            TokenRefreshResponse tokeRepo = new TokenRefreshResponse(newToken, requestRefreshToken);
            accessTokenService.deleteByUserId(token.getUser().getId());
            accessTokenService.createAccessToken(newToken);
            return responseResult.ResponseResult(
                    HttpStatus.OK,
                    CodeResponseConstant.AUTH.REFRESH_TOKEN_SUCCESS,
                    tokeRepo,
                    MessageResponseConstant.AUTH.REFRESH_TOKEN_SUCCESS
            );
        }
        catch(Exception e) {
            return responseResult.ResponseResult(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    CodeResponseConstant.SERVER_ERROR,
                    null,
                    MessageResponseConstant.SERVER_ERROR
            );
        }
    }

}
