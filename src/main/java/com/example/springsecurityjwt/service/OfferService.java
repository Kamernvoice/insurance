package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.assembler.OfferAssembler;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.repository.InsuranceTypeRepository;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springsecurityjwt.specification.OfferSpecification.*;

@Log
@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final InsuranceTypeRepository insuranceTypeRepository;
    private final UserRepository userRepository;
    private final OfferAssembler offerAssembler;
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

    public OfferDto findById(Integer id) {
        Offer offer = offerRepository.findOfferById(id);
        return offer != null ? offerAssembler.toModel(offer) : null;
    }

    public CollectionModel<OfferDto> findAll() {
        List<Offer> offers = offerRepository.findAll();
        return !offers.isEmpty() ? offerAssembler.toCollectionModel(offers) : null;
    }

    public CollectionModel<OfferDto> findAllByDescriptionContains(String search) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("cost").ascending().and(Sort.by("term").descending()));
        Page<Offer> offers = offerRepository.findAllByDescriptionContains(search, pageable);
        return !offers.isEmpty() ? offerAssembler.toCollectionModel(offers) : null;
    }

    public CollectionModel<OfferDto> filterAll(Integer minTerm, Integer maxTerm, Integer minCost, Integer maxCost, String description, Integer page, Integer size, String sort, String byWhat) {
        Specification<Offer> specification = Specification.where(minTerm != null || maxTerm != null ? termC(minTerm, maxTerm) : null)
                .and(minCost != null || maxCost != null ? costC(minCost, maxCost) : null)
                .and(description == null ? null : descriptionC(description));
        if(page == null) page = 1;
        if(size == null) size = 10;
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "cost");
        if(byWhat.equals("cost")) {
            if (sort.equals("ASC")) pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "cost");
            if (sort.equals("DESC")) pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "cost");
        }
        if(byWhat.equals("term")) {
            if(sort.equals("ASC")) pageRequest = PageRequest.of(page - 1, size, Sort.Direction.ASC, "term");
            if(sort.equals("DESC")) pageRequest = PageRequest.of(page - 1, size, Sort.Direction.DESC, "term");
        }
        Page<Offer> offers = offerRepository.findAll(specification, pageRequest);

        return !offers.isEmpty() ? offerAssembler.toCollectionModel(offers) : null;
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
