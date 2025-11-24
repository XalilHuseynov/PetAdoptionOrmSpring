package com.example.PetAdoptionSpring.service;

import com.example.PetAdoptionSpring.dto.AdopterRequestDto;
import com.example.PetAdoptionSpring.dto.AdopterResponseDto;
import com.example.PetAdoptionSpring.exception.custom.AdopterHasRequestsException;
import com.example.PetAdoptionSpring.exception.custom.AdopterNotFoundException;
import com.example.PetAdoptionSpring.exception.custom.PetNotFoundException;
import com.example.PetAdoptionSpring.mapper.AdopterMapper;
import com.example.PetAdoptionSpring.model.Adopter;
import com.example.PetAdoptionSpring.repository.AdopterRepository;
import com.example.PetAdoptionSpring.repository.AdoptionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdopterService {
    @Autowired
    private AdopterRepository adopterRepository;

    @Autowired
    private AdopterMapper adopterMapper;

    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    public List<AdopterResponseDto> getAll(){
        return adopterRepository.findAll().stream().map(adopterMapper::toDto).collect(Collectors.toList());
    }

    public AdopterResponseDto findById(Long id){
        Adopter adopter = adopterRepository.findById(id).orElseThrow(()->new AdopterNotFoundException("Adopter not found with ID : "+id));
        return adopterMapper.toDto(adopter);
    }

    public AdopterResponseDto create(AdopterRequestDto adopterRequestDto){
        Adopter adopter = adopterMapper.toEntity(adopterRequestDto);
        adopterRepository.save(adopter);
        return adopterMapper.toDto(adopter);
    }

    public AdopterResponseDto update(Long id, AdopterRequestDto adopterRequestDto){
        Adopter existing =adopterRepository.findById(id).orElseThrow(()->new AdopterNotFoundException("Adopter not found with ID : "+id));
        existing.setEmail(adopterRequestDto.getEmail());
        existing.setPhone(adopterRequestDto.getPhone());
        existing.setFullName(adopterRequestDto.getFullName());
        existing.setAddress(adopterRequestDto.getAddress());
        adopterRepository.save(existing);
        return adopterMapper.toDto(existing);
    }

    public void delete(Long id){
        Adopter adopter = adopterRepository.findById(id).orElseThrow(()->new AdopterNotFoundException("Adopter not found with ID : "+id));
        if(adoptionRequestRepository.findAll().stream().anyMatch(adoptionRequest-> Objects.equals(adoptionRequest.getAdopter().getId(), adopter.getId()))){
            throw new AdopterHasRequestsException("Adopter has request !");
        }
        adopterRepository.deleteById(id);
    }



}
