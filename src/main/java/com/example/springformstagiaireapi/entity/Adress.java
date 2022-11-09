package com.example.springformstagiaireapi.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "adress")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "adresse")
    private String adress;
    @Column(name = "code_postal")
    private Integer codepostal;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;

    // ============================================================
}
