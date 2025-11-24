package com.example.PetAdoptionSpring.mapper;

import com.example.PetAdoptionSpring.dto.PetRequestDto;
import com.example.PetAdoptionSpring.dto.PetResponseDto;
import com.example.PetAdoptionSpring.model.Pet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetMapper {
    Pet toEntity(PetRequestDto petRequestDto);
    PetResponseDto toDto(Pet pet);
}
