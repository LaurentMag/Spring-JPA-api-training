package com.example.springformstagiaireapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "matiere")
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom")
    private String nom;
    @Column(name = "duree")
    private Integer duree;
    @Column(name = "difficulte")
    private String difficulte;

    @ManyToMany
    @JoinTable(
            name = "matiere_formateur",
            joinColumns = @JoinColumn(name = "matiere_id"),
            inverseJoinColumns = @JoinColumn(name = "formateur_id")
    )
    private List<Formateur> listFormateurs;

    // ============================================================
}
