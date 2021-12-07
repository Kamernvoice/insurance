package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.Review;
import com.example.springsecurityjwt.repository.ReviewRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springsecurityjwt.entity.User;

import java.security.Principal;
import java.util.List;

@RestController
public class ReviewController {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(UserRepository userRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping(value = "/reviews/{id}")
    public ResponseEntity<?> create(@PathVariable(name = "id") int id, @RequestBody Review review, Principal loggedUser) {
        User client = userRepository.findByLogin(loggedUser.getName());
        review.setClient(client);
        review.setInsurer(userRepository.findUserById(id));
        reviewRepository.save(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/reviews/{id}")
    public ResponseEntity<List<Review>> findReviewByInsurer(@PathVariable(name = "id") int id) {
        User insurer = userRepository.findUserById(id);
        final List<Review> reviews = reviewRepository.findAllByInsurer(insurer);

        return reviews != null && !reviews.isEmpty()
                ? new ResponseEntity<>(reviews, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/reviews/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") int id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
