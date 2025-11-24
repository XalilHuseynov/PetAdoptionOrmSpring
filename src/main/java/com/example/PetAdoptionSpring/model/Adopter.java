package com.example.PetAdoptionSpring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "adopter",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AdoptionRequest> adoptionRequestList = new ArrayList<>();


}
