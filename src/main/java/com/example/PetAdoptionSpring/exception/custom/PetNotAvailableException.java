package com.example.PetAdoptionSpring.exception.custom;

public class PetNotAvailableException extends RuntimeException {
  public PetNotAvailableException(String message) {
    super(message);
  }
}
