package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.InsuranceType;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.repository.InsuranceTypeRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class OfferConverter {

    @Autowired
    private InsuranceTypeRepository insuranceTypeRepository;
    @Autowired
    private UserRepository userRepository;

    public OfferConverter(InsuranceTypeRepository insuranceTypeRepository, UserRepository userRepository) {
        this.insuranceTypeRepository = insuranceTypeRepository;
        this.userRepository = userRepository;
    }

    public Offer fromOfferDtoToOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setInsuranceType(insuranceTypeRepository.findInsuranceTypeById(offerDto.getInsuranceTypeId()));
        offer.setInsurer(userRepository.findUserById(offerDto.getInsurerId()));
        offer.setTerm(offerDto.getTerm());
        offer.setCost(offerDto.getCost());
        offer.setDescription(offerDto.getDescription());
        return offer;
    }

    public OfferDto fromOfferToOfferDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .insuranceTypeId(offer.getInsuranceType().getId())
                .insurerId(offer.getInsurer().getId())
                .term(offer.getTerm())
                .cost(offer.getCost())
                .description(offer.getDescription())
                .build();
    }
}
