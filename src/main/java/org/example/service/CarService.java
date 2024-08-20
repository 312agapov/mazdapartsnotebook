package org.example.service;

import org.example.entity.Car;
import org.example.entity.Part;
import org.example.repository.CarRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public void addCar(Car car){
        if(car.getMark().isEmpty() || car.getModel().isEmpty() || car.getBuildYear() < 1885){
            throw new IllegalStateException("Марка и модель авто не должны быть пустыми, первый авто был создан только в 1886 году!");
        } else {
            carRepository.save(car);
        }
    }

    public void addCarToOwner(UUID ownerId, UUID carId){
        if(userRepository.existsById(ownerId) && carRepository.existsById(carId)) {
            Car wiredCar = carRepository.getReferenceById(carId);
            if (userRepository.findById(ownerId).isPresent()) {
                wiredCar.setOwner(userRepository.findById(ownerId).get());
            } else {
                throw new IllegalStateException("Нет такого пользователя для установления владельца для авто!");
            }
            carRepository.save(wiredCar);
        } else {
            throw new IllegalStateException("Авто или пользователя не существует!");
        }
    }

    public Car editCarById(UUID id, Car car){
        if(!carRepository.existsById(id)){
            throw new IllegalStateException("Авто с таким ID не существует!");
        }else if(car.getMark().isEmpty() || car.getModel().isEmpty() || car.getBuildYear() < 1885){
            throw new IllegalStateException("Марка и модель авто не должны быть пустыми, первый авто был создан только в 1886 году!");
        }else {
            Car existCar = carRepository.findById(id).orElseThrow(() ->
                    new IllegalStateException("Авто не был найден в БД!"));
            existCar.setMark(car.getMark());
            existCar.setModel(car.getModel());
            existCar.setBuildYear(car.getBuildYear());
            return carRepository.save(existCar);
        }
    }

    public int findTotalCarPartsPrice(UUID carId){
        if(!carRepository.existsById(carId)){
            throw new IllegalStateException("Авто с таким ID не существует!");
        }
        Car partsOfCar = carRepository.getReferenceById(carId);
        List<Part> partList = partsOfCar.getParts();
        int partsSum = 0;
        for(Part part : partList){
            partsSum += part.getPrice();
        }
        return partsSum;
    }

    public int findSumOfUserCarsParts(UUID ownerId){
        return carRepository.findSumOfUserCarsParts(ownerId);
    }

    public void deleteCarById(UUID id){
        if(!carRepository.existsById(id)){
            throw new IllegalStateException("Авто с таким ID не существует!");
        }else{
            carRepository.deleteById(id);
        }
    }

    public List<Part> findAllCarParts(UUID carId){
        if(!carRepository.existsById(carId)){
            throw new IllegalStateException("Авто с таким ID не существует!");
        } else {
        return carRepository.getReferenceById(carId).getParts();
        }
    }
}
