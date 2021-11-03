package com.example.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Integer id;
    private String name;
    private String login;
    private String role;
    private Integer phone;
    private Double rating;
}
