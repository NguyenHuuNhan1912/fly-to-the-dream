package com.ten.ten.services;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.dtos.RefreshTokenDto;
import com.ten.ten.entities.RefreshTokenEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface RefreshTokenService {


    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);

    RefreshTokenEntity createRefreshToken(String username);

    int deleteByUserId(String id);

    ResponseEntity<ResponseResult> refreshToken(RefreshTokenDto refreshTokenDto);
}
