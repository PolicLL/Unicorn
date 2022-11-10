package com.example.unicorn.dao;

import com.example.unicorn.entity.WarehouseEntity;
import org.springframework.data.repository.CrudRepository;

public interface WarehouseRepository extends CrudRepository<WarehouseEntity, Long> {
}
