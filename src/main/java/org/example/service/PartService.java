package org.example.service;

import org.example.entity.Car;
import org.example.entity.Part;
import org.example.repository.CarRepository;
import org.example.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PartService {
    @Autowired
    PartRepository partRepository;

    @Autowired
    CarRepository carRepository;

    public void addPart(UUID carId, Part part){
        if(part.getPartNumber().isEmpty() || part.getPrice() < 0 || part.getDescription().isEmpty()){
            throw new IllegalStateException("У детали должно быть описание, номер детали и цена больше нуля!");
        } else {
            Car carToLink = carRepository.findById(carId).orElseThrow(() ->
                    new IllegalStateException("Авто не был найден в БД!"));
            partRepository.save(part);
            if(carToLink.getParts() != null){
                carToLink.getParts().add(part);
            } else {
                List<Part> list = new ArrayList<Part>();
                list.add(part);
                carToLink.setParts(list);
            }
            carRepository.save(carToLink);
        }
    }
}
