package org.example.controller;

import org.example.entity.Car;
import org.example.entity.Part;
import org.example.service.CarService;
import org.example.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/part")
public class PartController {

    @Autowired
    CarService carService;
    @Autowired
    PartService partService;

    @PostMapping(path = "/{carId}")
    public ResponseEntity<Part> addPart(@PathVariable ("carId") UUID carId, @RequestBody Part part){
        partService.addPart(carId,part);
        return new ResponseEntity<>(part, HttpStatus.OK);
    }

}
