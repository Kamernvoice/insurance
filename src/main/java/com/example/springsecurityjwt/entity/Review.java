package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String review;

    @Column
    private Double grade;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "insurer_id")
    private User insurer;
}
