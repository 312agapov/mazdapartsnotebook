package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

}
