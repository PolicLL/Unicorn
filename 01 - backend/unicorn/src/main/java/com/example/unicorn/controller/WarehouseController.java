package com.example.unicorn.controller;

import com.example.unicorn.dao.BrandRepository;
import com.example.unicorn.dao.CarPartRepository;
import com.example.unicorn.dao.CarRepository;
import com.example.unicorn.dao.WarehouseRepository;
import com.example.unicorn.entity.BrandEntity;
import com.example.unicorn.entity.CarEntity;
import com.example.unicorn.entity.CarPartEntity;
import com.example.unicorn.entity.WarehouseEntity;
import com.example.unicorn.entity.json.CarPartReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarPartRepository carPartRepository;

    // Create

    @PostMapping
    public void saveWarehouse(@RequestBody WarehouseEntity warehouseEntity) {
        warehouseRepository.save(warehouseEntity);
    }

    // Read

    @GetMapping
    public Iterable<WarehouseEntity> getWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping("/{id}")
    public WarehouseEntity getWarehouse(@PathVariable(value = "id") Long id) {

        Optional<WarehouseEntity> tempWarehouse = warehouseRepository.findById(id);

        if(tempWarehouse.isPresent())
            return tempWarehouse.get();

        return new WarehouseEntity();

    }

    @GetMapping("/{warehouseId}/count/{brandName}/{carName}")
    public CarPartReport getCountOfCarPartsInWarehouse
            (@PathVariable Long warehouseId ,
             @PathVariable String brandName ,
             @PathVariable String carName) {

        CarPartReport carPartsReport = new CarPartReport();

        try{

            BrandEntity tempBrandEntity = brandRepository.findBrandByBrandName(brandName);
            CarEntity tempCarEntity = carRepository.findCarByCarName(carName);
            WarehouseEntity warehouseEntity = warehouseRepository.findById(warehouseId).get();

            if(doesBrandContainsCar(tempBrandEntity , tempCarEntity))
                return createCarPartReport(tempCarEntity , tempBrandEntity , warehouseEntity);

        }
        catch (NullPointerException exception){
            exception.printStackTrace();
        }

        return carPartsReport;
    }

    private CarPartReport createCarPartReport(CarEntity tempCarEntity , BrandEntity tempBrandEntity , WarehouseEntity warehouseEntity){
        CarPartReport carPartsReport = new CarPartReport();

        carPartsReport.setCarName(tempCarEntity.getName());
        carPartsReport.setBrandName(tempBrandEntity.getName());
        carPartsReport.setCount(getNumberOfCarPartsInWarehouse(tempCarEntity , warehouseEntity));

        return carPartsReport;
    }


    @GetMapping("/test")
    public void test() {

        Optional<WarehouseEntity> warehouseEntity = warehouseRepository.findById(1L);
        Optional<WarehouseEntity> warehouseEntity1 = warehouseRepository.findById(2L);
        Optional<WarehouseEntity> warehouseEntity2 = warehouseRepository.findById(3L);

        Optional<CarEntity> tempCar = carRepository.findById(1L);

        System.out.println("Size : " + warehouseEntity.get().getCarPartEntities().size());
        System.out.println("Size : " + warehouseEntity1.get().getCarPartEntities().size());
        System.out.println("Size : " + warehouseEntity2.get().getCarPartEntities().size());

        System.out.println();

        System.out.println(getNumberOfCarPartsInWarehouse(tempCar.get() , warehouseEntity.get()));


    }



    private int getNumberOfCarPartsInWarehouse(CarEntity carEntity , WarehouseEntity warehouseEntity){
        List <CarPartEntity> carPartEntities = carEntity.getCarPartEntities();

        int numberOfCarPartsInWarehouse = 0;

        for(CarPartEntity carPart : carPartEntities)
            if(isCarPartInWarehouse(carPart , warehouseEntity))
                ++numberOfCarPartsInWarehouse;

        return numberOfCarPartsInWarehouse;
    }

    private boolean isCarPartInWarehouse(CarPartEntity carPart , WarehouseEntity warehouseEntity){
        return (carPart.getWarehouseEntity().getWarehouseId() == warehouseEntity.getWarehouseId());
    }

    // Search

    // http://localhost:4411/api/warehouses/search?start=2016-05-09&end=2018-10-12

    @GetMapping("/search")
    public List<CarPartEntity> getCarPartsByStartEndDate
    (@RequestParam("start") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate ,
     @RequestParam("end") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) {

        return carPartRepository.findByStartBeforeAndEndAfter(startDate , endDate);

    }

    @GetMapping("/search/{brandName}/{carName}")
    public List<CarPartEntity> getCarPartsByCarAndBrandName
            (@PathVariable String brandName ,
             @PathVariable String carName) {

        try {

            BrandEntity tempBrandEntity = brandRepository.findBrandByBrandName(brandName);
            CarEntity tempCarEntity = carRepository.findCarByCarName(carName);

            if(doesBrandContainsCar(tempBrandEntity , tempCarEntity))
                return carRepository.findCarByCarName(carName).getCarPartEntities();

        }
        catch (Exception e){
            e.printStackTrace();

        }

        return new ArrayList<>();
    }

    private boolean doesBrandContainsCar(BrandEntity tempBrandEntity , CarEntity tempCarEntity){
        return tempBrandEntity.getCarEntities().contains(tempCarEntity);
    }

    // Update

    @GetMapping("/update")
    public void updateWarehouse(@RequestBody WarehouseEntity warehouseEntity){
        warehouseRepository.save(warehouseEntity);
    }

    // Delete

    @GetMapping("/delete/{id}")
    public void deleteWarehouse(@PathVariable(value = "id") Long id){
        warehouseRepository.deleteById(id);
    }

}