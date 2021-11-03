package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "contracts")
@Data
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date contractDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column
    private Boolean insurerConfirmContractStatus;

    @Column
    private Boolean insurerConfirmPaymentStatus;

    @Column
    private Boolean clientIncidentStatus;

    @Column
    private Boolean insurerConfirmIncidentStatus;

    @Column
    private Boolean clientConfirmPaymentStatus;

}
