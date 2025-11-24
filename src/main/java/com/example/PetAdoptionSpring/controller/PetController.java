package com.example.PetAdoptionSpring.controller;


import com.example.PetAdoptionSpring.dto.PetRequestDto;
import com.example.PetAdoptionSpring.dto.PetResponseDto;
import com.example.PetAdoptionSpring.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping()
    public ResponseEntity<List<PetResponseDto>> getAll(){
        return ResponseEntity.ok(petService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        petService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<PetResponseDto> create(@Valid @RequestBody PetRequestDto petRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.create(petRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PetResponseDto> update(@PathVariable Long id,@Valid @RequestBody PetRequestDto petRequestDto){
        return ResponseEntity.ok(petService.update(id,petRequestDto));
    }

}
