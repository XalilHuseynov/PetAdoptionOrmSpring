package com.example.PetAdoptionSpring.exception.custom;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException(String message) {
        super(message);
    }
}
