package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Contract;
import com.example.springsecurityjwt.entity.Offer;
import com.example.springsecurityjwt.entity.Role;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
    User findAllByRole(Role role);
    User findUserById(Integer id);
    User findUserByContracts(Contract contract);
    User findUserByOffers(Offer offer);
}
