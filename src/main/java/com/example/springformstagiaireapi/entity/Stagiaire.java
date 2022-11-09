package com.example.springformstagiaireapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "stagiaire")
public class Stagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "civilite")
    private Civilite civilite;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "email")
    private String email;
    @Column(name = "date_naissance")
    private String dateNaissance;

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name ="formateur_id", nullable = false)
    private Formateur formateur;

    // ============================================================

}
