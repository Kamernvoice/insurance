package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    List<Contract> findAllByClient(User client);
    List<Contract> findAllByOffer(Offer offer);
    List<Contract> findAllByOffer_Id(Integer id);
}
