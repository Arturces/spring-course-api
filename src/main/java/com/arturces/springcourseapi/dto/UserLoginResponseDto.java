package com.arturces.springcourseapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserLoginResponseDto implements Serializable {
    private static final long serialVersionUID = 5297304718860374942L;

    private String token;
    private Long expireIn;
    private String tokenProvider;
}
