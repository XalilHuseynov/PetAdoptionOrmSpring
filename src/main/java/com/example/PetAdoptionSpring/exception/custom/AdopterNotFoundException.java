package com.example.PetAdoptionSpring.exception.custom;

public class AdopterNotFoundException extends RuntimeException {
    public AdopterNotFoundException(String message) {
        super(message);
    }
}
