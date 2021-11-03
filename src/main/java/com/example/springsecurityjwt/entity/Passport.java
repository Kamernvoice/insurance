package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "passports")
@Data
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "client_id")
    private User client;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date birthDate;

    @Column
    private String passportNumber;

    @Column
    private String identificationNumber;

    @Column
    private Date issueDate;

    @Column
    private String issuingAuthority;

    @Column
    private Byte[] passportPhoto;

    @Column
    private Boolean confirmation;
}