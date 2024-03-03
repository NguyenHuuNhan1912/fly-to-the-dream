package com.ten.ten.controllers;


import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.URLConstant;
import com.ten.ten.dtos.RefreshTokenDto;
import com.ten.ten.dtos.TokenRefreshResponse;
import com.ten.ten.entities.RefreshTokenEntity;
import com.ten.ten.exceptions.TokenRefreshException;
import com.ten.ten.security.JwtTokenProvider;
import com.ten.ten.services.AccessTokenService;
import com.ten.ten.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URLConstant.BASE_URL_V1)
public class RefreshTokenController {

    @Autowired
    private RefreshTokenService refreshTokenService;


    @PostMapping(URLConstant.AUTH.REFRESH_TOKEN)
    public ResponseEntity<ResponseResult> refreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return refreshTokenService.refreshToken(refreshTokenDto);
    }
}
