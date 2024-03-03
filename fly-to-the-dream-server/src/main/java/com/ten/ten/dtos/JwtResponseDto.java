package com.ten.ten.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponseDto {

    private String jwtToken;
    private String username;

}
