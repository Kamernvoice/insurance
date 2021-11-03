package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "offers")
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "insurance_type_id")
    private InsuranceType insuranceType;

    @ManyToOne
    @JoinColumn(name = "insurer_id")
    private User insurer;

    @Column
    private Integer term;

    @Column
    private BigDecimal cost;

    @Column
    private String description;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;
}
