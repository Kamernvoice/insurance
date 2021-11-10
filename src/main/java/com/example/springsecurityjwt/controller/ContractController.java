package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {

    private final ContractRepository contractRepository;

    @Autowired
    public ContractController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @PostMapping(value = "/contracts")
    public ResponseEntity<?> create(@RequestBody Contract contract) {
        contractRepository.save(contract);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/contracts")
    public ResponseEntity<List<Contract>> findAll() {
        final List<Contract> contracts = contractRepository.findAll();

        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/offers/contracts")
    public ResponseEntity<List<Contract>> findContractsByOffer(@RequestBody Offer offer) {
        final List<Contract> contracts = contractRepository.findAllByOffer(offer);
        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/contracts/{id}")
    public ResponseEntity<Contract> findContractById(@PathVariable(name = "id") int id) {
        final Contract contract = contractRepository.getById(id);

        return contract != null
                ? new ResponseEntity<>(contract, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/contracts/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Contract contract) {
        if (contractRepository.existsById(id)) {
            contract.setId(id);
            final Contract updated = contractRepository.save(contract);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/contracts/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
