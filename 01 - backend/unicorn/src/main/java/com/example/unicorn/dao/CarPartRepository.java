package com.example.unicorn.dao;

import com.example.unicorn.entity.CarPartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarPartRepository extends CrudRepository<CarPartEntity, Long> {

    @Query("FROM CarPartEntity part WHERE part.dateCreated >= :date AND part.dateCreated <= :dateCopy")
    List<CarPartEntity> findByStartBeforeAndEndAfter(@Param("date") LocalDate date, @Param("dateCopy") LocalDate dateCopy);



}
