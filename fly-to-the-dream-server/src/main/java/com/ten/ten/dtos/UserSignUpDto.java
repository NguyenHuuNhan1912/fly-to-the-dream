package com.ten.ten.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserSignUpDto {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Instant dateOfBirth;
    private Boolean gender;
}
