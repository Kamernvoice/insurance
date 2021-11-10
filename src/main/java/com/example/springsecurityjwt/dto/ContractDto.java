package com.example.springsecurityjwt.dto;

import com.example.springsecurityjwt.entity.Contract;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "contract")
@Relation(collectionRelation = "contracts")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContractDto extends RepresentationModel<ContractDto> {

    private Integer id;
    private Date contractDate;
    private Integer clientId;
    private String clientName;
    private Integer offerId;
    private Boolean insurerConfirmContractStatus;
    private Boolean insurerConfirmPaymentStatus;
    private Boolean clientIncidentStatus;
    private Boolean insurerConfirmIncidentStatus;
    private Boolean clientConfirmPaymentStatus;
}
