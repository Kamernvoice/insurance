package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column
    private Integer phone;

    @Column
    private byte[] photo;

    @Column
    private Double rating;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "insurer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;
}
