package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.InsuranceType;
import com.example.springsecurityjwt.repository.InsuranceTypeRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@Component
@Log
@RestController
public class InsuranceTypeController {

    private final InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    public InsuranceTypeController(InsuranceTypeRepository insuranceTypeRepository) {

        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    @GetMapping(value = "/types")
    public ResponseEntity<List<InsuranceType>> findAll() {
        final List<InsuranceType> insuranceTypes = insuranceTypeRepository.findAll();

        return !insuranceTypes.isEmpty()
                ? ResponseEntity.ok(insuranceTypes)
                : ResponseEntity.notFound().build();
    }
}