package com.example.unicorn.controller;

import com.example.unicorn.dao.BrandRepository;
import com.example.unicorn.dao.CarPartRepository;
import com.example.unicorn.dao.CarRepository;
import com.example.unicorn.entity.CarPartEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/carParts")
public class CarPartController {

    @Autowired
    CarPartRepository carPartRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CarRepository carRepository;


    @PostMapping
    public void saveCarPart(@RequestBody CarPartEntity CarPartEntity) {

        carPartRepository.save(CarPartEntity);
    }

    @GetMapping
    public Iterable<CarPartEntity> getCarParts() {

        return carPartRepository.findAll();
    }

    @GetMapping("/{id}")
    public CarPartEntity getCarPartById(@PathVariable(value = "id") Long id) {

        Optional<CarPartEntity> tempCarPart = carPartRepository.findById(id);

        return tempCarPart.orElseGet(CarPartEntity::new);

    }

    @PutMapping()
    public void updateCarPart(@RequestBody CarPartEntity carPartEntity){
        carPartRepository.save(carPartEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCarPart(@PathVariable(value = "id") Long id){
        carPartRepository.deleteById(id);
    }

}