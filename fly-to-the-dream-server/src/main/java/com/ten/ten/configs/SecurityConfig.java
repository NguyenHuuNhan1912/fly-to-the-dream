package com.ten.ten.configs;

import com.ten.ten.constants.URLConstant;
import com.ten.ten.security.JwtAuthenticationEntryPoint;
import com.ten.ten.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity

public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/api/v1/products").permitAll()
                .requestMatchers("/api/v1/delete-product").permitAll()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.AUTH.SIGN_IN).permitAll()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.AUTH.SIGN_UP).permitAll()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.AUTH.SIGN_OUT).authenticated()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.USER.SEARCH_USER).authenticated()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.AUTH.REFRESH_TOKEN).permitAll()
                .requestMatchers(URLConstant.BASE_URL_V1 + URLConstant.AUTH.ADD_NEW_ROLE).authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
