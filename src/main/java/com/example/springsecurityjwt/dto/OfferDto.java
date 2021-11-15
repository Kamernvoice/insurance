package com.example.springsecurityjwt.dto;

import com.example.springsecurityjwt.entity.Contract;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "offer")
@Relation(collectionRelation = "offers")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferDto extends RepresentationModel<OfferDto> {

    private Integer id;
    private Integer insuranceTypeId;
    private String insuranceTypeName;
    private Integer insurerId;
    private String insurerName;
    @Min(value = 5, message = "Age should not be less than 18")
    private Integer term;
    @Min(0)
    private BigDecimal cost;
    @NotBlank
    private String description;
    private List<ContractDto> contracts;
}
