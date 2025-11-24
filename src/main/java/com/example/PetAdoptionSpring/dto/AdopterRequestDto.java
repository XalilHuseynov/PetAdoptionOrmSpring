package com.example.PetAdoptionSpring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdopterRequestDto {
    @NotBlank(message = "Invalid full name !")
    private String fullName;
    @Email(message = "Invalid email !")
    private String email;
    @NotBlank(message = "Invalid phone number ! ")
    private String phone;
    @NotBlank(message = "Invalid adress !")
    private String address;
}
