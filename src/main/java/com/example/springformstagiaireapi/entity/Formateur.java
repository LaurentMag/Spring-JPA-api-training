package com.example.springformstagiaireapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "formateur")
public class Formateur {

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
    @Column(name = "date_embauche")
    private String dateEmbauche;
    @Column(name = "temp_experience")
    private Integer nivexp;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusExterneInterne status;

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    @JsonBackReference
    @OneToMany(mappedBy = "formateur")
    private List<Stagiaire> listStagiaire;

    @JsonBackReference
    @ManyToMany(mappedBy = "listFormateurs")
    private List<Matiere> listMatieres;

    // ============================================================

}
