package com.example.springsecurityjwt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Builder
public class OfferDto {

    private Integer id;
    private Integer insuranceTypeId;
    private Integer insurerId;
    private Integer term;
    @Min(0)
    private BigDecimal cost;
    private String description;
}
