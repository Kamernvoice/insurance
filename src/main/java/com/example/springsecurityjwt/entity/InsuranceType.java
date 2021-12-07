package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "insurance_types")
@Data
public class InsuranceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String insuranceType;
}
