package com.example.PetAdoptionSpring.exception.custom;

public class AdopterHasRequestsException extends RuntimeException {
    public AdopterHasRequestsException(String message) {
        super(message);
    }
}
