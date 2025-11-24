package com.example.PetAdoptionSpring.repository;

import com.example.PetAdoptionSpring.model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptionRequestRepository  extends JpaRepository<AdoptionRequest,Long> {
}
