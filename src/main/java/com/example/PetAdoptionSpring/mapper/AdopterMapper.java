package com.example.PetAdoptionSpring.mapper;

import com.example.PetAdoptionSpring.dto.AdopterRequestDto;
import com.example.PetAdoptionSpring.dto.AdopterResponseDto;
import com.example.PetAdoptionSpring.model.Adopter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdopterMapper {
    Adopter toEntity(AdopterRequestDto adopterRequestDto);
    AdopterResponseDto toDto(Adopter adopter);
}
