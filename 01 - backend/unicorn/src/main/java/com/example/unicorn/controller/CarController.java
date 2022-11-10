package com.example.unicorn.controller;

import com.example.unicorn.dao.BrandRepository;
import com.example.unicorn.dao.CarRepository;
import com.example.unicorn.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    BrandRepository brandRepository;

    @PostMapping
    public void saveCar(@RequestBody CarEntity carEntity) {

        if(brandRepository.existsById(carEntity.getBrandEntity().getBrandId()))
            carRepository.save(carEntity);
    }

    @GetMapping
    public Iterable<CarEntity> getCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public CarEntity getCar(@PathVariable(value = "id") Long id) {

        Optional<CarEntity> tempCar = carRepository.findById(id);

        if(tempCar.isPresent())
            return tempCar.get();

        return new CarEntity();

    }

    @PutMapping("/update")
    public void updateCar(@RequestBody CarEntity carEntity){
        carRepository.save(carEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCar(@PathVariable(value = "id") Long id){
        carRepository.deleteById(id);
    }

}
