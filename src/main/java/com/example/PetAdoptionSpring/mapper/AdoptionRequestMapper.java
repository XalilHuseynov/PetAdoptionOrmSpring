package com.example.PetAdoptionSpring.mapper;

import com.example.PetAdoptionSpring.dto.AdoptionRequestRequestDto;
import com.example.PetAdoptionSpring.dto.AdoptionRequestResponseDto;
import com.example.PetAdoptionSpring.model.AdoptionRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdoptionRequestMapper {
    AdoptionRequest toEntity(AdoptionRequestRequestDto adoptionRequestRequestDto);
    AdoptionRequestResponseDto toDto(AdoptionRequest adoptionRequest);
}
