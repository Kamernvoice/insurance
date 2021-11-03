package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Licence;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenceRepository extends JpaRepository<Licence, Integer> {

    Licence findLicenceByInsurer(User insurer);
}
