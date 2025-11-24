package com.example.PetAdoptionSpring.service;

import com.example.PetAdoptionSpring.dto.PetRequestDto;
import com.example.PetAdoptionSpring.dto.PetResponseDto;
import com.example.PetAdoptionSpring.exception.custom.PetHasRequestsException;
import com.example.PetAdoptionSpring.exception.custom.PetNotFoundException;
import com.example.PetAdoptionSpring.mapper.PetMapper;
import com.example.PetAdoptionSpring.model.Pet;
import com.example.PetAdoptionSpring.repository.AdoptionRequestRepository;
import com.example.PetAdoptionSpring.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    public List<PetResponseDto> getAll(){
        return petRepository.findAll().stream()
                .map(petMapper::toDto).collect(Collectors.toList());
    }

    public PetResponseDto findById(Long id){
        Pet pet = petRepository.findById(id).orElseThrow(()->new PetNotFoundException("Pet not found with ID : "+id));
        return petMapper.toDto(pet);
    }

    public PetResponseDto create(PetRequestDto petRequestDto){
        Pet pet = petMapper.toEntity(petRequestDto);
        petRepository.save(pet);
        return petMapper.toDto(pet);
    }

    public PetResponseDto update(Long id,PetRequestDto petRequestDto){
        Pet existing = petRepository.findById(id).orElseThrow(()->new PetNotFoundException("Pet not found with ID : "+id));
        existing.setAge(petRequestDto.getAge());
        existing.setName(petRequestDto.getName());
        existing.setAvailable(petRequestDto.isAvailable());
        existing.setGender(petRequestDto.getGender());
        existing.setVaccinated(petRequestDto.isVaccinated());
        existing.setSpecies(petRequestDto.getSpecies());
        petRepository.save(existing);
        return petMapper.toDto(existing);
    }

    public void deleteById(Long id){
        Pet pet = petRepository.findById(id).orElseThrow(()->new PetNotFoundException("Pet not found with ID : "+id));
        if(adoptionRequestRepository.findAll().stream().anyMatch(adoptionRequest ->
                Objects.equals(adoptionRequest.getPet().getId(), pet.getId()))){
            throw new PetHasRequestsException("Pet has Requests !");
        }
        petRepository.deleteById(id);
    }


}
