package com.example.springformstagiaireapi.repository;

import com.example.springformstagiaireapi.entity.Adress;
import com.example.springformstagiaireapi.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
}
