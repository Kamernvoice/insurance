package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.repository.OfferRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferConverter offerConverter;

    public OfferDto save(OfferDto offerDto) {
        Offer saved = offerRepository.save(offerConverter.fromOfferDtoToOffer(offerDto));
        return offerConverter.fromOfferToOfferDto(saved);
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
            offerRepository.save(offerConverter.fromOfferDtoToOffer(offerDto));
            return true;
        }
        return false;
    }

    public OfferDto findOfferDtoById(Integer id) {
        Offer offer = offerRepository.findOfferById(id);
        if (offer != null) {
            return offerConverter.fromOfferToOfferDto(offer);
        }
        return null;
    }

    public List<OfferDto> findAll() {
        return offerRepository.findAll()
                .stream()
                .map(offerConverter::fromOfferToOfferDto)
                .collect(Collectors.toList());
    }

    public Page<OfferDto> findAllSort(Pageable pageable) {
        List<OfferDto> offersDto = offerRepository.findAll(pageable)
                .stream()
                .map(offerConverter::fromOfferToOfferDto)
                .collect(Collectors.toList());
        return new PageImpl<>(offersDto);
    }

    public Page<OfferDto> findAllByDescriptionContains(String search, Pageable pageable) {
        List<OfferDto> offersDto = offerRepository.findAllByDescriptionContains(search, pageable)
                .stream()
                .map(offerConverter::fromOfferToOfferDto)
                .collect(Collectors.toList());
        return new PageImpl<>(offersDto);
    }
}
