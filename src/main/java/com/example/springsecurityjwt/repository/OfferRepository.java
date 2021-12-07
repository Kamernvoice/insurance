package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.InsuranceType;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {


    Offer findOfferById(Integer id);
    Page<Offer> findAllByDescriptionContains(String search, Pageable pageable);
    List<Offer> findAllByInsurer(User insurer);
    Offer  findOfferByContracts(Contract contract);
}
