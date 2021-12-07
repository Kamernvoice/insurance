package com.example.springsecurityjwt.controller.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String name;
    private String login;
    private String password;
    private Integer phone;
}
