package com.example.springformstagiaireapi.repository;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
}
