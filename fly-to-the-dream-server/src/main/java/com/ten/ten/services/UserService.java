package com.ten.ten.services;

import com.ten.ten.commons.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseResult> getAllUsers();

    ResponseEntity<ResponseResult> searchUser(HttpServletRequest request);
}
