package com.example.springsecurityjwt.assembler;

import com.example.springsecurityjwt.controller.ContractController;
import com.example.springsecurityjwt.controller.OfferController;
import com.example.springsecurityjwt.dto.ContractDto;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OfferAssembler extends RepresentationModelAssemblerSupport<Offer, OfferDto> {


    public OfferAssembler() {
        super(OfferController.class, OfferDto.class);
    }

    @Override
    public CollectionModel<OfferDto> toCollectionModel(Iterable<? extends Offer> entities)
    {
        CollectionModel<OfferDto> actorModels = super.toCollectionModel(entities);

        actorModels.add(linkTo(methodOn(OfferController.class).findAll(Pageable.unpaged())).withSelfRel());

        return actorModels;
    }

    @Override
    public OfferDto toModel(Offer offer) {
        OfferDto offerDto = instantiateModel(offer);

        offerDto.add(linkTo(
                methodOn(OfferController.class)
                        .findOfferById(offer.getId()))
                .withSelfRel());

        offerDto.setId(offer.getId());
        offerDto.setInsuranceTypeId(offer.getInsuranceType().getId());
        offerDto.setInsuranceTypeName(offer.getInsuranceType().getInsuranceType());
        offerDto.setInsurerId(offer.getInsurer().getId());
        offerDto.setInsurerName(offer.getInsurer().getName());
        offerDto.setTerm(offer.getTerm());
        offerDto.setCost(offer.getCost());
        offerDto.setDescription(offer.getDescription());
        offerDto.setContracts(toContractModel(offer.getContracts()));
        return offerDto;
    }

    private List<ContractDto> toContractModel(List<Contract> contracts) {
        if (contracts.isEmpty())
            return Collections.emptyList();

        return contracts.stream()
                .map(contract -> ContractDto.builder()
                        .id(contract.getId())
                        .contractDate(contract.getContractDate())
                        .clientId(contract.getClient().getId())
                        .clientName(contract.getClient().getName())
                        .offerId(contract.getOffer().getId())
                        .insurerConfirmContractStatus(contract.getInsurerConfirmContractStatus())
                        .insurerConfirmPaymentStatus(contract.getInsurerConfirmPaymentStatus())
                        .clientIncidentStatus(contract.getClientIncidentStatus())
                        .insurerConfirmIncidentStatus(contract.getInsurerConfirmIncidentStatus())
                        .clientConfirmPaymentStatus(contract.getClientConfirmPaymentStatus())
                        .build()
                        .add(linkTo(
                                methodOn(ContractController.class)
                                        .findContractById(contract.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
