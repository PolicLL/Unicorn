package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "car_part")
public class CarPartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number")
    private Long serialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "date_created")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;

    @OneToOne(mappedBy = "carPartEntity")
    @JsonIgnoreProperties("carPartEntity")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ArticleEntity articleEntity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("carPartEntities")
    @ToString.Exclude@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private WarehouseEntity warehouseEntity;

    @ManyToMany
    @JoinTable(name = "car_and_car_parts" , joinColumns = @JoinColumn(name = "car_part_id")
                                          , inverseJoinColumns = @JoinColumn(name = "car_id"))
    @JsonIgnoreProperties("carPartEntities")
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CarEntity> carEntities;

}
