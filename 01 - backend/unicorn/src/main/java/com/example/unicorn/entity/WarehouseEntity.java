package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouse")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "warehouseEntity")
    @JsonIgnoreProperties("warehouseEntity")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CarPartEntity> carPartEntities;


}

