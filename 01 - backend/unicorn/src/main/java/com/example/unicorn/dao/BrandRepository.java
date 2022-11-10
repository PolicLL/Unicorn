package com.example.unicorn.dao;

import com.example.unicorn.entity.BrandEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BrandRepository extends CrudRepository<BrandEntity, Long> {

    @Query("FROM BrandEntity b WHERE b.name = :brandName")
    BrandEntity findBrandByBrandName(@Param("brandName") String brandName);


}
