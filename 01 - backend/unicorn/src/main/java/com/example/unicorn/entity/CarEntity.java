package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "brand_id" , nullable = false)
    @JsonIgnoreProperties("carEntities")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // brand_id - name of column by which Car is connected to Brand
    private BrandEntity brandEntity;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "car_and_car_parts" , joinColumns = @JoinColumn(name = "car_id")
                                          , inverseJoinColumns = @JoinColumn(name = "car_part_id"))
    @JsonIgnoreProperties("carEntities")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CarPartEntity> carPartEntities;
}

