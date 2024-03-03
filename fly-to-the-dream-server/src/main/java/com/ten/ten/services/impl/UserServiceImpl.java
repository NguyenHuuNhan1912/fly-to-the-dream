package com.ten.ten.services.impl;
import com.ten.ten.commons.ResponseResult;
import com.ten.ten.dtos.SearchUserResponse;
import com.ten.ten.entities.UserEntity;
import com.ten.ten.repositories.UserReporitory;
import com.ten.ten.security.CustomUserDetails;
import com.ten.ten.security.JwtTokenProvider;
import com.ten.ten.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserReporitory userReporitory;

    @Override
    public ResponseEntity<ResponseResult> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(200, userReporitory.getAllUsers(), "SUCCESS"));
    }

    @Override
    public ResponseEntity<ResponseResult> searchUser(HttpServletRequest request) {
        String requestAccessToken = tokenProvider.getTokenFromRequest(request);
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        UserEntity user =  userReporitory.searchUser(userId);
        SearchUserResponse searchUserResponse = new SearchUserResponse();
        searchUserResponse.setUsername(user.getUsername());
        searchUserResponse.setEmail(user.getEmail());
        searchUserResponse.setGender(user.getGender());
        searchUserResponse.setPhoneNumber(user.getPhoneNumber());
        searchUserResponse.setDateOfBirth(user.getDateOfBirth());
        searchUserResponse.setRoleEntities(user.getRoleEntities());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(200, searchUserResponse, "SUCCESS"));
    }
}
