package com.example.unicorn.dao;

import com.example.unicorn.entity.DiscountEntity;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<DiscountEntity, Long> {
}
