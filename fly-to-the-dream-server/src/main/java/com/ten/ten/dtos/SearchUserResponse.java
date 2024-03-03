package com.ten.ten.dtos;

import com.ten.ten.entities.RoleEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserResponse {

    private String username;

    private String email;

    private Boolean gender;

    private String phoneNumber;

    private Instant dateOfBirth;

    private Set<RoleEntity> roleEntities = new HashSet<>();

}
