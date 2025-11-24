package com.example.PetAdoptionSpring.dto;

import lombok.Data;

@Data
public class AdopterResponseDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
}
