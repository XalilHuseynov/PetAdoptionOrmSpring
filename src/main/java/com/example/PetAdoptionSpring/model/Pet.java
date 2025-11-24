package com.example.PetAdoptionSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private int age;
    private String gender;
    private boolean vaccinated;
    private boolean available;

    @OneToMany(mappedBy = "pet",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<AdoptionRequest> adoptionRequestList = new ArrayList<>();


}
