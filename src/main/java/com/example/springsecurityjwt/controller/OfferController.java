package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.assembler.OfferAssembler;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.repository.OfferRepository;
import com.example.springsecurityjwt.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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
    public ResponseEntity<CollectionModel<OfferDto>> findAll() {
        final CollectionModel<OfferDto> offers = offerService.findAll();

        return offers != null
                ? new ResponseEntity<>(offers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<CollectionModel<OfferDto>> search(@RequestParam String search) {

        final CollectionModel<OfferDto> offers = offerService.findAllByDescriptionContains(search);

        return offers != null
                ? new ResponseEntity<>(offers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<CollectionModel<OfferDto>> filterAll(@RequestParam(required = false)
                                                                 Integer maxTerm, Integer minTerm,
                                                                 Integer maxCost, Integer minCost,
                                                                 String search,
                                                                 Integer page, Integer size,
                                                                 String sort, String byWhat) {

        final CollectionModel<OfferDto> offers = offerService.filterAll(minTerm, maxTerm, minCost, maxCost, search, page, size, sort, byWhat);

        return offers != null
                ? new ResponseEntity<>(offers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/offers/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable(name = "id") int id) {

        final OfferDto offerDto = offerService.findById(id);

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