package com.ten.ten.services;

import com.ten.ten.entities.AccessToken;

import java.util.Optional;

public interface AccessTokenService {

    AccessToken createAccessToken(String token);

    void deleteByUserId(String id);

    void deleteToken(String token);

}
