package com.example.Backend.entity.member;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    // Constructors, getters, and setters

    public enum RoleName {
        ADMINISTRATOR,
        SELLER,
        CUSTOMER
    }
}

