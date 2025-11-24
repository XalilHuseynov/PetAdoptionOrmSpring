package com.example.PetAdoptionSpring.dto;

import com.example.PetAdoptionSpring.enums.STATUS;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdoptionRequestRequestDto {
    @NotBlank(message = "Status can't be empty or null")
    private STATUS status;

}
