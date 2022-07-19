package com.example.usermodule.request;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String email;
    private String password;
}
