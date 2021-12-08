package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.repository.InsuranceTypeRepository;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.example.springsecurityjwt.specification.OfferSpecification.*;

@Log
@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final UserRepository userRepository;
    private final ContractService contractService;

    public OfferDto save(OfferDto offerDto) {
        Offer saved = offerRepository.save(this.fromOfferDtoToOffer(offerDto));
        return this.fromOfferToOfferDto(saved);
    }

    public boolean delete(Integer id) {
        if (offerRepository.existsById(id)) {
            offerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean update(Integer id, OfferDto offerDto) {
        if (offerRepository.existsById(id)) {
            offerDto.setId(id);
            offerRepository.save(this.fromOfferDtoToOffer(offerDto));
            return true;
        }
        return false;
    }

    public Offer findById(Integer id) {
        return offerRepository.findOfferById(id);
    }

    public Page<Offer> findAll(Pageable defaultPageable) {
        return offerRepository.findAll(defaultPageable);
    }

    public Page<Offer> findAllByDescriptionContains(String search, Integer page, Integer size, String sort, Pageable defaultPageable) {
        Pageable pageable;
        if (page == null || size == null || sort == null) {
            pageable = defaultPageable;
        } else {
            pageable = PageRequest.of(page, size, defaultPageable.getSort());
        }
        return offerRepository.findAllByDescriptionContains(search, pageable);
    }

    public Page<Offer> filterAll(Integer minTerm, Integer maxTerm, Integer minCost, Integer maxCost, String description, Integer page, Integer size, String sort, Pageable defaultPageable) {
        Specification<Offer> specification = Specification.where(minTerm != null || maxTerm != null ? termC(minTerm, maxTerm) : null)
                .and(minCost != null || maxCost != null ? costC(minCost, maxCost) : null)
                .and(description == null ? null : descriptionC(description));
        Pageable pageable;
        if (page == null || size == null || sort == null) {
            pageable = defaultPageable;
        } else {
            pageable = PageRequest.of(page, size, defaultPageable.getSort());
        }
        return offerRepository.findAll(specification, pageable);
    }

    public Offer fromOfferDtoToOffer(OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setId(offerDto.getId());
        offer.setInsuranceType(insuranceTypeRepository.findInsuranceTypeById(offerDto.getInsuranceTypeId()));
        offer.setInsurer(userRepository.findUserById(offerDto.getInsurerId()));
        offer.setTerm(offerDto.getTerm());
        offer.setCost(offerDto.getCost());
        offer.setDescription(offerDto.getDescription());
        if(offerDto.getContracts() != null) {
            offer.setContracts(offerDto.getContracts().stream()
                    .map(contractService::fromContractDtoToContract)
                    .collect(Collectors.toList()));
        }
        return offer;
    }

    public OfferDto fromOfferToOfferDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .insuranceTypeId(offer.getInsuranceType().getId())
                .insuranceTypeName(offer.getInsuranceType().getInsuranceType())
                .insurerId(offer.getInsurer().getId())
                .insurerName(offer.getInsurer().getName())
                .term(offer.getTerm())
                .cost(offer.getCost())
                .description(offer.getDescription())
                .contracts(offer.getContracts() != null
                        ? offer.getContracts().stream()
                        .map(contractService::fromContractToContractDto)
                        .collect(Collectors.toList())
                        : null)
                .build();
    }
}
