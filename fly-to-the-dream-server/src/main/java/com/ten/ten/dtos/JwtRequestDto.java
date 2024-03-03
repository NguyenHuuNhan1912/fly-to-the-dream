package com.ten.ten.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class JwtRequestDto {
    private String email;
    private String password;
}
