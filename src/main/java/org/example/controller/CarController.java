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
@RequestMapping(path = "/car")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private PartService partService;

    @GetMapping
    public List<Car> findAllCars(){
        return carService.findAllCars();
    }

    @GetMapping(path = "/{carId}")
    public ResponseEntity<List<Part>> findAllCarParts(@PathVariable ("carId") UUID carId){
        return ResponseEntity.ok(carService.findAllCarParts(carId));
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        carService.addCar(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping(path = "/total/{carId}")
    public int findTotal(@PathVariable ("carId") UUID carId){
        return carService.findTotalCarPartsPrice(carId);
    }

    @GetMapping(path = "/owner/{ownerId}")
    public int findSumOfUserParts(@PathVariable ("ownerId") UUID ownerId){
        return carService.findSumOfUserCarsParts(ownerId);
    }

    @PostMapping(path = "/addcartoowner/{ownerId}")
    public ResponseEntity<Car> addCarToOwner(@PathVariable ("ownerId") UUID ownerId, @RequestParam UUID carId){
        carService.addCarToOwner(ownerId,carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{carId}")
    public ResponseEntity<Car> editCarById(@PathVariable ("carId") UUID id, @RequestBody Car car){
        Car editedCar = carService.editCarById(id,car);
        return new ResponseEntity<>(editedCar, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable ("carId") UUID id){
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
