package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.InsuranceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceType, Integer> {

    InsuranceType findInsuranceTypeById(Integer id);
    InsuranceType findInsuranceTypeByInsuranceType(String insuranceType);
}
