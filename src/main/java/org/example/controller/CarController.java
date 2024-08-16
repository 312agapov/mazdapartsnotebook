package org.example.controller;

import org.example.entity.Car;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public List<Car> findAllCars(){
        return carService.findAllCars();
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping(path = "/{carId}")
    public ResponseEntity<Car> editCarById(@PathVariable ("carId") UUID id, @RequestBody Car car){
        Car editedCar = carService.editCarById(id,car);
        return new ResponseEntity<>(editedCar, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{carId}")
    public ResponseEntity deleteCarById(@PathVariable ("carId") UUID id){
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
