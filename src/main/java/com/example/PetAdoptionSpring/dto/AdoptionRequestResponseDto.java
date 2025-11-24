package com.example.PetAdoptionSpring.dto;

import com.example.PetAdoptionSpring.enums.STATUS;
import lombok.Data;

import java.time.LocalDate;
@Data
public class AdoptionRequestResponseDto {
    private Long id;
    private LocalDate requestDate;
    private STATUS status;
}
