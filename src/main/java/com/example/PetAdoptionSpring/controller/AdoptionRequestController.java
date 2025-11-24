package com.example.PetAdoptionSpring.controller;


import com.example.PetAdoptionSpring.dto.AdoptionRequestRequestDto;
import com.example.PetAdoptionSpring.dto.AdoptionRequestResponseDto;
import com.example.PetAdoptionSpring.repository.AdoptionRequestRepository;
import com.example.PetAdoptionSpring.service.AdoptionRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class AdoptionRequestController {
    @Autowired
    private AdoptionRequestService adoptionRequestService;

    @GetMapping()
    public ResponseEntity<List<AdoptionRequestResponseDto>> getAll(){
        return ResponseEntity.ok(adoptionRequestService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionRequestResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(adoptionRequestService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        adoptionRequestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdoptionRequestResponseDto> update(@PathVariable Long id, @Valid @RequestBody AdoptionRequestRequestDto adoptionRequestRequestDto){
        return ResponseEntity.ok(adoptionRequestService.update(id,adoptionRequestRequestDto));
    }

    @PostMapping()
    public ResponseEntity<AdoptionRequestResponseDto> create(
            @RequestParam(name = "petId") Long petId,
            @RequestParam(name = "adopterId") Long adopterId

    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionRequestService.create(petId,adopterId));
    }



}