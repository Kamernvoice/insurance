package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.assembler.OfferAssembler;
import com.example.springsecurityjwt.dto.OfferDto;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.exception.CustomNotFoundException;
import com.example.springsecurityjwt.service.OfferService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Set;

@Log
@RestController
@AllArgsConstructor
public class OfferController {

    private final PagedResourcesAssembler<Offer> pagedResourcesAssembler;

    private final OfferService offerService;
    private final OfferAssembler offerAssembler;
    private final ValidatorFactory factory;
    private final Validator validator;

    @Autowired
    public OfferController(OfferService offerService, OfferAssembler offerAssembler) {

        this.offerService = offerService;
        this.pagedResourcesAssembler = new PagedResourcesAssembler<>(null, null);
        this.offerAssembler = offerAssembler;
        this.factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @PostMapping(value = "/offers")
    public ResponseEntity<?> save(@RequestBody @Valid OfferDto offerDto) {

        Set<ConstraintViolation<OfferDto>> violations = validator.validate(offerDto);
        for (ConstraintViolation<OfferDto> violation : violations) {
            log.severe(violation.getMessage());
        }
        offerService.save(offerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/offers")
    public ResponseEntity<CollectionModel<OfferDto>> findAll(@PageableDefault(sort = {"cost"}, direction = Sort.Direction.ASC) Pageable defaultPageable) {
        final Page<Offer> offers = offerService.findAll(defaultPageable);

        PagedModel<OfferDto> dto = pagedResourcesAssembler.toModel(offers, offerAssembler);
        return !offers.isEmpty()
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/search")
    public ResponseEntity<CollectionModel<OfferDto>> search(@RequestParam(required = false) String search,
                                                            Integer page, Integer size, String sort,
                                                            @PageableDefault(sort = {"cost"}, direction = Sort.Direction.ASC) Pageable defaultPageable) throws Exception{

        final Page<Offer> offers = offerService.findAllByDescriptionContains(search, page, size, sort, defaultPageable);
        if(offers.isEmpty()) throw new CustomNotFoundException("Offer not found");
        PagedModel<OfferDto> dto = pagedResourcesAssembler.toModel(offers, offerAssembler);
        return !offers.isEmpty()
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/filter")
    public ResponseEntity<PagedModel<OfferDto>> filterAll(@RequestParam(required = false)
                                                                 Integer maxTerm, Integer minTerm,
                                                                 Integer maxCost, Integer minCost,
                                                                 String search,
                                                                 Integer page, Integer size, String sort,
                                                          @PageableDefault(sort = {"cost"}, direction = Sort.Direction.ASC) Pageable defaultPageable) {

        final Page<Offer> offers = offerService.filterAll(minTerm, maxTerm, minCost, maxCost, search, page, size, sort, defaultPageable);

        PagedModel<OfferDto> dto = pagedResourcesAssembler.toModel(offers, offerAssembler);
        return !offers.isEmpty()
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/offers/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable(name = "id") int id) {

        final Offer offer = offerService.findById(id);

        OfferDto offerDto = offerAssembler.toModel(offer);
        return offerDto != null
                ? ResponseEntity.ok(offerDto)
                : ResponseEntity.notFound().build();
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