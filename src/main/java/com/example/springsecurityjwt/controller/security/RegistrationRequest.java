package com.example.springsecurityjwt.controller.security;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private String login;

    @NotEmpty
    @Size(min = 4, max = Integer.MAX_VALUE)
    private String password;

    @NotEmpty
    private String role;

    private Integer phone;
}
