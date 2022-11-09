package com.example.springformstagiaireapi.repository;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
}
