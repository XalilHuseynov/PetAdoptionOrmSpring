package com.example.PetAdoptionSpring.repository;

import com.example.PetAdoptionSpring.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter,Long> {
}
