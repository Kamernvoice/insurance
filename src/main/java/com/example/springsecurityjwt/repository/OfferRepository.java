package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.InsuranceType;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {

    Offer findOfferById(Integer id);
    Page<Offer> findAll(Pageable pageable);
    List<Offer> findAll();
    Slice<Offer> findAllByInsurer(User insurer, Pageable pageable);
    Page<Offer> findAllByDescriptionContains(String search, Pageable pageable);
    List<Offer> findAllByInsurer(User insurer);
    List<Offer> findAllByInsuranceType(InsuranceType insuranceType);
    List<Offer> findAllByInsuranceTypeAndCost(InsuranceType insuranceType, BigDecimal cost);
    List<Offer> findAllByInsuranceTypeAndTerm(InsuranceType insuranceType, Integer term);
    List<Offer> findAllByInsuranceTypeAndCostAndTerm(InsuranceType insuranceType, BigDecimal cost, Integer term);
    Offer  findOfferByContracts(Contract contract);
}
