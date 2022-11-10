package com.example.unicorn.dao;

import com.example.unicorn.entity.CarEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends CrudRepository<CarEntity, Long> {

    @Query("FROM CarEntity c WHERE c.name = :carName")
    CarEntity findCarByCarName(@Param("carName") String carName);
}
