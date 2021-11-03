package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Review;
import com.example.springsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByInsurer(User insurer);
}
