package com.example.springsecurityjwt.controller.security;

import com.example.springsecurityjwt.entity.InsuranceType;
import com.example.springsecurityjwt.repository.InsuranceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestSecurityController {

    private final InsuranceTypeRepository insuranceTypeRepository;

    @Autowired
    TestSecurityController(InsuranceTypeRepository insuranceTypeRepository) {
        this.insuranceTypeRepository = insuranceTypeRepository;
    }

    @GetMapping("/admin/get")
    public String getAdmin() {
        return "Hi admin";
    }

    @GetMapping("/client/get")
    public String getClient() {
        return "Hi client";
    }

    @GetMapping("/insurer/get")
    public String getInsurer() {return "Hi insurer";}

    @GetMapping("/index")
    public List<InsuranceType> getIndex() {
        return insuranceTypeRepository.findAll();
    }
}
