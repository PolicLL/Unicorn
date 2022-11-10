package com.example.unicorn.controller;

import com.example.unicorn.dao.BrandRepository;
import com.example.unicorn.dao.DiscountRepository;
import com.example.unicorn.entity.DiscountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    @Autowired
    DiscountRepository discountRepository;

    @Autowired
    BrandRepository brandRepository;

    @PostMapping
    public void saveDiscount(@RequestBody DiscountEntity discountEntity) {

        discountRepository.save(discountEntity);
    }

    @GetMapping
    public Iterable<DiscountEntity> getDiscounts() {
        return discountRepository.findAll();
    }

    @GetMapping("/{id}")
    public DiscountEntity getDiscount(@PathVariable(value = "id") Long id) {

        Optional<DiscountEntity> tempDiscount = discountRepository.findById(id);

        return tempDiscount.orElseGet(DiscountEntity::new);

    }

    @PutMapping("/update")
    public void updateDiscount(@RequestBody DiscountEntity discountEntity){
        discountRepository.save(discountEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDiscount(@PathVariable(value = "id") Long id){
        discountRepository.deleteById(id);
    }

}