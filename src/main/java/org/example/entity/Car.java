package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String mark;
    private String model;
    private int buildYear;

    //@ManyToOne
    //private User owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
    private List<Part> parts;

}
