package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Passport;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Integer> {

    Passport findPassportByClient(User client);
}
