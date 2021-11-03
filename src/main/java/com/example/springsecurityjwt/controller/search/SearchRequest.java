package com.example.springsecurityjwt.controller.search;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchRequest {

    @NotEmpty
    private String search;

}
