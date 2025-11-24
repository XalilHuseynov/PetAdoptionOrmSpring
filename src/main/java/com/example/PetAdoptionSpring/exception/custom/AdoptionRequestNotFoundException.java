package com.example.PetAdoptionSpring.exception.custom;

public class AdoptionRequestNotFoundException extends RuntimeException {
    public AdoptionRequestNotFoundException(String message) {
        super(message);
    }
}
