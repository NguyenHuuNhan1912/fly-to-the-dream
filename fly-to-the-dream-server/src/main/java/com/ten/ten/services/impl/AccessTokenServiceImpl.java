package com.ten.ten.services.impl;

import com.ten.ten.entities.AccessToken;
import com.ten.ten.repositories.AccessTokenRepository;
import com.ten.ten.repositories.UserReporitory;
import com.ten.ten.security.JwtTokenProvider;
import com.ten.ten.services.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    @Value("${app-jwt-expiration-milliseconds}")
    private Long accessTokenDurationMs;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AccessTokenRepository accessTokenRepository;
    @Autowired
    private UserReporitory userReporitory;

    @Override
    public AccessToken createAccessToken(String token) {
        AccessToken accessToken = new AccessToken();
        String username = jwtTokenProvider.getUsername(token);
        accessToken.setUser(userReporitory.findByUsername(username));
        accessToken.setExpiryDate(Instant.now().plusMillis(accessTokenDurationMs));
        accessToken.setToken(token);
        accessToken = accessTokenRepository.save(accessToken);
        return accessToken;
    }

    @Transactional
    public void deleteByUserId(String id) {
        accessTokenRepository.deleteByUserId(id);
    }

    @Transactional
    public void deleteToken(String token) {
        if (token != null) {
            accessTokenRepository.deleteToken(token);
        }
    }
}
