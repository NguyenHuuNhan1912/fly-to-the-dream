package com.ten.ten.services.impl;

import com.ten.ten.commons.ResponseResult;
import com.ten.ten.configs.SecurityConfig;
import com.ten.ten.constants.CodeResponseConstant;
import com.ten.ten.constants.MessageResponseConstant;
import com.ten.ten.dtos.LoginDto;
import com.ten.ten.dtos.TokenRefreshResponse;
import com.ten.ten.dtos.UserSignUpDto;
import com.ten.ten.entities.RefreshTokenEntity;
import com.ten.ten.entities.RoleEntity;
import com.ten.ten.entities.UserEntity;
import com.ten.ten.repositories.RoleRepository;
import com.ten.ten.repositories.UserReporitory;
import com.ten.ten.security.CustomUserDetails;
import com.ten.ten.security.JwtTokenProvider;
import com.ten.ten.services.AccessTokenService;
import com.ten.ten.services.AuthService;
import com.ten.ten.services.RefreshTokenService;
import com.ten.ten.validations.AuthValidate;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    UserReporitory userReporitory;

    @Autowired
    AccessTokenService accessTokenService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AuthValidate authValidate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ResponseResult responseResult;

    @Override
    public ResponseEntity<ResponseResult> login(LoginDto loginDto) {
        try {
            UserEntity user = null;
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();
            user = userReporitory.findByUsername(username);
            Boolean isValidUsername = authValidate.isValidUsername(username);
            Boolean isValidPassword = authValidate.isValidPassword(password);

            if (!isValidUsername) return responseResult.
                    ResponseResult(
                            HttpStatus.BAD_REQUEST,
                            CodeResponseConstant.AUTH.INVALID_USERNAME,
                            null,
                            MessageResponseConstant.AUTH.INVALID_USERNAME
                    );

            if (!isValidPassword)
                return responseResult.
                        ResponseResult(
                                HttpStatus.BAD_REQUEST,
                                CodeResponseConstant.AUTH.INVALID_PASSWORD,
                                null,
                                MessageResponseConstant.AUTH.INVALID_PASSWORD)
                        ;
            if (!Objects.isNull(user)) {
                if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                    return responseResult
                            .ResponseResult(
                                    HttpStatus.BAD_REQUEST,
                                    CodeResponseConstant.AUTH.WRONG_EMAIL_OR_PASSWORD,
                                    null,
                                    MessageResponseConstant.AUTH.WRONG_USERNAME_OR_PASSWORD
                            );
                }

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getUsername(),
                                loginDto.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                String token = tokenProvider.generateToken(userDetails.getUsername());
                accessTokenService.createAccessToken(token);
                RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());
                TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse(token, refreshToken.getToken());
                logger.info("Login success ! Logger");
                return responseResult
                        .ResponseResult(
                                HttpStatus.OK,
                                CodeResponseConstant.AUTH.SUCCESS,
                                tokenRefreshResponse,
                                MessageResponseConstant.AUTH.SUCCESS
                        );
            } else {
                return responseResult
                        .ResponseResult(
                                HttpStatus.BAD_REQUEST,
                                CodeResponseConstant.AUTH.WRONG_EMAIL_OR_PASSWORD,
                                null,
                                MessageResponseConstant.AUTH.WRONG_USERNAME_OR_PASSWORD
                        );
            }
        } catch (Exception e) {
            return responseResult
                    .ResponseResult(
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            CodeResponseConstant.SERVER_ERROR,
                            null,
                            MessageResponseConstant.SERVER_ERROR
                    );
        }
    }

    @Override
    public ResponseEntity<ResponseResult> signUp(UserSignUpDto signUpDto) {
        try {

            if(!authValidate.isValidUsername(signUpDto.getUsername())) {
                return responseResult
                        .ResponseResult(
                                HttpStatus.BAD_REQUEST,
                                CodeResponseConstant.AUTH.INVALID_USERNAME,
                                null,
                                MessageResponseConstant.AUTH.INVALID_USERNAME
                        );
            }

            if(!authValidate.isValidPassword(signUpDto.getPassword())) {
                return responseResult
                        .ResponseResult(
                                HttpStatus.BAD_REQUEST,
                                CodeResponseConstant.AUTH.INVALID_PASSWORD,
                                null,
                                MessageResponseConstant.AUTH.INVALID_PASSWORD
                        );
            }

            if(!authValidate.isValidPhoneNumber(signUpDto.getPhoneNumber())) {
                return responseResult.ResponseResult(HttpStatus.BAD_REQUEST, CodeResponseConstant.AUTH.INVALID_PHONE_NUMBER, null, MessageResponseConstant.AUTH.INVALID_PHONE_NUMBER);
            }

            if(!authValidate.isValidEmail(signUpDto.getEmail())) {
                return responseResult
                        .ResponseResult(
                                HttpStatus.BAD_REQUEST,
                                CodeResponseConstant.AUTH.INVALID_EMAIL,
                                null,
                                MessageResponseConstant.AUTH.INVALID_EMAIL
                        );
            }

            UserEntity user = userReporitory.findByUsername(signUpDto.getUsername());

            if(!Objects.isNull(user)) {
               return responseResult
                       .ResponseResult(
                               HttpStatus.CONFLICT,
                               CodeResponseConstant.AUTH.ACCOUNT_EXIST,
                               null,
                               MessageResponseConstant.AUTH.ACCOUNT_EXIST
                       );
            }

            UserEntity newUser = new UserEntity();
            String encodedPassword = SecurityConfig.passwordEncoder().encode(signUpDto.getPassword());
            newUser.setAllUserEntity(
                    signUpDto.getUsername(),
                    encodedPassword,
                    signUpDto.getEmail(),
                    signUpDto.getGender(),
                    signUpDto.getPhoneNumber(),
                    signUpDto.getDateOfBirth()
            );
            Set<RoleEntity> roles = new HashSet<>();
            roles.add(roleRepository.findByNameRole("USER_ROLE"));
            newUser.setRoleEntities(roles);
            userReporitory.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseResult(CodeResponseConstant.AUTH.SUCCESS, userReporitory.getAllUsers(), "SUCCESS"));
        }
        catch(Exception e) {
            return responseResult.ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR, CodeResponseConstant.SERVER_ERROR, null, MessageResponseConstant.SERVER_ERROR);
        }
    }


    public ResponseEntity<ResponseResult> signOut(HttpServletRequest request) {
        try {
            String requestAccessToken = tokenProvider.getTokenFromRequest(request);
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String userId = userDetails.getId();
            refreshTokenService.deleteByUserId(userId);
            accessTokenService.deleteToken(requestAccessToken);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseResult(100, "Logout success !"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseResult(500, "server"));
        }
    }
}
