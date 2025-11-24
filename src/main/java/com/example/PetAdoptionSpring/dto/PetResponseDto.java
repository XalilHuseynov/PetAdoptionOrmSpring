package com.example.PetAdoptionSpring.dto;

import lombok.Data;

@Data
public class PetResponseDto {
    private Long id;
    private String name;
    private String species;
    private int age;
    private String gender;
    private boolean vaccinated;
    private boolean available;
}
