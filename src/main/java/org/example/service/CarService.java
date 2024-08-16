package org.example.service;

import org.example.entity.Car;
import org.example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

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

    public void deleteCarById(UUID id){
        if(!carRepository.existsById(id)){
            throw new IllegalStateException("Авто с таким ID не существует!");
        }else{
            carRepository.deleteById(id);
        }
    }
}
