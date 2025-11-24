package com.example.PetAdoptionSpring.controller;


import com.example.PetAdoptionSpring.dto.AdopterRequestDto;
import com.example.PetAdoptionSpring.dto.AdopterResponseDto;
import com.example.PetAdoptionSpring.service.AdopterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopters")
public class AdopterController {
    @Autowired
    private AdopterService adopterService;

    @GetMapping()
    public ResponseEntity<List<AdopterResponseDto>> getAll(){
        return ResponseEntity.ok(adopterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdopterResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(adopterService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        adopterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<AdopterResponseDto> create(@Valid @RequestBody AdopterRequestDto adopterRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(adopterService.create(adopterRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdopterResponseDto> update(@PathVariable Long id,@Valid @RequestBody AdopterRequestDto adopterRequestDto){
        return ResponseEntity.ok(adopterService.update(id,adopterRequestDto));
    }







}
