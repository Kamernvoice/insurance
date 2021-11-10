package com.example.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserDto {

    private Integer id;
    @NotBlank
    private String name;
    @Email
    private String login;
    private String role;
    @Min(111111111) @Max(999999999)
    private Integer phone;
    @Min(0) @Max(5)
    private Double rating;
}
