package com.ten.ten.services.impl;

import com.ten.ten.entities.UserEntity;
import com.ten.ten.repositories.UserReporitory;
import com.ten.ten.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserReporitory userReporitory;

    public UserDetailServiceImpl(UserReporitory userReporitory) {
        this.userReporitory = userReporitory;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userReporitory.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> authorities = user
                .getRoleEntities()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toSet());
        return new CustomUserDetails(user);
    }
}
