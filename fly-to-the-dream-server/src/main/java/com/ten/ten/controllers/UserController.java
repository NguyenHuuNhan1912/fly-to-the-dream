package com.ten.ten.controllers;


import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.URLConstant;
import com.ten.ten.dtos.LoginDto;
import com.ten.ten.dtos.UserSignUpDto;
import com.ten.ten.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.BASE_URL_V1)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(URLConstant.USER.SEARCH_USER)
    public ResponseEntity<ResponseResult> searchUser(HttpServletRequest request) {
        return userService.searchUser(request);
    }
}
