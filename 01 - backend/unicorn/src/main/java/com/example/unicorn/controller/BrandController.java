package com.example.unicorn.controller;

import com.example.unicorn.dao.BrandRepository;
import com.example.unicorn.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    @PostMapping
    public void saveBrand(@RequestBody BrandEntity brandEntity) {
        brandRepository.save(brandEntity);
    }

    @GetMapping
    public Iterable<BrandEntity> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public BrandEntity getBrand(@PathVariable(value = "id") Long id) {

        Optional<BrandEntity> tempBrand = brandRepository.findById(id);

        return tempBrand.orElseGet(BrandEntity::new);

    }

    @GetMapping("/update")
    public void updateBrand(@RequestBody BrandEntity brandEntity){
        brandRepository.save(brandEntity);
    }

    @GetMapping("/delete/{id}")
    public void deleteBrand(@PathVariable(value = "id") Long id){
        brandRepository.deleteById(id);
    }
}
