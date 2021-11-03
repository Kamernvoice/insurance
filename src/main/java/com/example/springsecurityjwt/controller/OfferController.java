package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.controller.search.SearchRequest;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Licence;
import com.example.springsecurityjwt.entity.User;
import com.example.springsecurityjwt.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class OfferController {

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {

        this.offerService = offerService;
    }

    @PostMapping(value = "/offers")
    public ResponseEntity<?> save(@RequestBody @Valid OfferDto offerDto) {
        offerService.save(offerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/offers")
    public ResponseEntity<List<OfferDto>> findAll() {
        final List<OfferDto> offersDto = offerService.findAll();

        return offersDto != null && !offersDto.isEmpty()
                ? new ResponseEntity<>(offersDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/offersSort")
    public ResponseEntity<Page<OfferDto>> findAllSort() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("cost").ascending().and(Sort.by("term").descending()));
        final Page<OfferDto> offersDto = offerService.findAllSort(pageable);

        return offersDto != null &&  !offersDto.isEmpty()
                ? new ResponseEntity<>(offersDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<OfferDto>> filterAll(@RequestBody SearchRequest search) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("cost").ascending().and(Sort.by("term").descending()));
        final Page<OfferDto> offersDto = offerService.findAllByDescriptionContains(search.getSearch(), pageable);
        return offersDto != null &&  !offersDto.isEmpty()
                ? new ResponseEntity<>(offersDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/offers/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable(name = "id") int id) {
        final OfferDto offerDto = offerService.findOfferDtoById(id);

        return offerDto != null
                ? new ResponseEntity<>(offerDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/offers/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody OfferDto offerDto) {
        final boolean updated = offerService.update(id, offerDto);

        return updated
            ? new ResponseEntity<>(HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/offers/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = offerService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}