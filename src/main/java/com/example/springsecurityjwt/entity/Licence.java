package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "licences")
@Data
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "insurer_id")
    private User insurer;

    @Column
    private String insurerName;

    @Column
    private String address;

    @Column
    private Integer taxpayerIdentificationNumber;

    @Column
    private String licenceNumber;

    @Column
    private Date issueDecisionDate;

    @Column
    private Integer issueDecisionNumber;

    @Column
    private Boolean confirmation;
}
