package com.example.PetAdoptionSpring.service;

import com.example.PetAdoptionSpring.enums.STATUS;
import com.example.PetAdoptionSpring.dto.AdoptionRequestRequestDto;
import com.example.PetAdoptionSpring.dto.AdoptionRequestResponseDto;
import com.example.PetAdoptionSpring.exception.custom.*;
import com.example.PetAdoptionSpring.mapper.AdoptionRequestMapper;
import com.example.PetAdoptionSpring.model.Adopter;
import com.example.PetAdoptionSpring.model.AdoptionRequest;
import com.example.PetAdoptionSpring.model.Pet;
import com.example.PetAdoptionSpring.repository.AdopterRepository;
import com.example.PetAdoptionSpring.repository.AdoptionRequestRepository;
import com.example.PetAdoptionSpring.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdoptionRequestService {
    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    private AdoptionRequestMapper adoptionRequestMapper;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdopterRepository adopterRepository;

    public List<AdoptionRequestResponseDto> getAll(){
        return adoptionRequestRepository.findAll().stream().
                map(adoptionRequestMapper::toDto).collect(Collectors.toList());
    }

    public AdoptionRequestResponseDto findById(Long id){
        AdoptionRequest adoptionRequest = adoptionRequestRepository.findById(id).orElseThrow(()->new AdoptionRequestNotFoundException("Adoption request not found with ID "+id));
        return adoptionRequestMapper.toDto(adoptionRequest);
    }

    public AdoptionRequestResponseDto create(Long petId, Long adopterId){
        Pet pet = petRepository.findById(petId).orElseThrow(()->new PetNotFoundException("Pet not found with ID : "+petId));
        Adopter adopter =adopterRepository.findById(adopterId).orElseThrow(()->new AdopterNotFoundException("Adopter not found with ID : "+adopterId));

        if(!pet.isAvailable()){
            throw new PetNotAvailableException("Pet is not available  !");
        }
        if(!adopter.getAdoptionRequestList().isEmpty()){
            throw new AdopterHasRequestsException("Adopter has already request ! ");
        }

        if(!pet.getAdoptionRequestList().isEmpty()){
            throw new PetHasRequestsException("Pet already adopted ! ");
        }


        AdoptionRequest adoptionRequest = new AdoptionRequest();
        adoptionRequest.setPet(pet);
        adoptionRequest.setAdopter(adopter);
        adoptionRequest.setStatus(STATUS.PENDING);
        adoptionRequest.setRequestDate(LocalDate.now());
        adoptionRequestRepository.save(adoptionRequest);
        return adoptionRequestMapper.toDto(adoptionRequest);
    }

    public void deleteById(Long id){
        adoptionRequestRepository.deleteById(id);
    }

    public AdoptionRequestResponseDto update(Long id, AdoptionRequestRequestDto adoptionRequestRequestDto){
        if(adoptionRequestRequestDto.getStatus().equals(STATUS.APPROVED)){
            AdoptionRequest adoptionRequest = adoptionRequestRepository.findById(id).orElseThrow(()->new AdoptionRequestNotFoundException("Adoption request not found with ID "+id));
            adoptionRequest.getPet().setAvailable(false);
            petRepository.save(adoptionRequest.getPet());
        }
        AdoptionRequest existing = adoptionRequestRepository.findById(id).orElseThrow(()->new AdoptionRequestNotFoundException("Adoption request not found with ID "+id));
        existing.setStatus(adoptionRequestRequestDto.getStatus());
        adoptionRequestRepository.save(existing);
        return adoptionRequestMapper.toDto(existing);
    }


}
