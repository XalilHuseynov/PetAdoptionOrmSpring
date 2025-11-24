package com.example.PetAdoptionSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PetRequestDto {
    @NotBlank(message = "Invalid name !")
    private String name;
    @NotBlank(message = "Species can't be empty or null")
    private String species;
    @Positive(message = "Age must be positive")
    private int age;
    @NotBlank(message = "Gender is invalid")
    private String gender;
    private boolean vaccinated;
    private boolean available;
}
