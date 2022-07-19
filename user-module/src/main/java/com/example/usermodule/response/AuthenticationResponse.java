package com.example.usermodule.response;

import com.example.usermodule.model.Authority;
import com.example.usermodule.model.Doctorant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private Long id;
    private String email;
    private String fullName;
    private String jwt;
    private List<String> authorities;

}
