package com.example.springsecurityjwt.controller.search;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class FilterRequest {

    private List<Integer> insuranceTypesId;
    private List<Integer> insurersId;
    private Integer termMin;
    private Integer termMax;
    private BigDecimal costMin;
    private BigDecimal costMax;
}
