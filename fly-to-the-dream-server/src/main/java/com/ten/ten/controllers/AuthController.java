package com.ten.ten.controllers;
import com.ten.ten.commons.ResponseResult;
import com.ten.ten.constants.URLConstant;
import com.ten.ten.dtos.*;
import com.ten.ten.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstant.BASE_URL_V1)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;


    @PostMapping(URLConstant.AUTH.SIGN_IN)
    public ResponseEntity<ResponseResult> getAllUsers(@RequestBody LoginDto loginDto) {
       return authService.login(loginDto);
    }

    @PostMapping(URLConstant.AUTH.SIGN_UP)
    public ResponseEntity<ResponseResult> signUpNewUser(@RequestBody UserSignUpDto signUpDto) {
        return authService.signUp(signUpDto);
    }


    @GetMapping(URLConstant.AUTH.SIGN_OUT)
    public ResponseEntity<ResponseResult> logoutUser(HttpServletRequest request ) {
        return authService.signOut(request);
    }
}
