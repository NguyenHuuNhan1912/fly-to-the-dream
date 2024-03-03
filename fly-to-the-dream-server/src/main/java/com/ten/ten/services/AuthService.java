package com.ten.ten.services;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.dtos.LoginDto;
import com.ten.ten.dtos.UserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseResult> login(LoginDto loginDto);

    ResponseEntity<ResponseResult> signUp(UserSignUpDto signUpDto);

    ResponseEntity<ResponseResult> signOut(HttpServletRequest request);
}
