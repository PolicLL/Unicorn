package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "brand")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "name")
    private String name;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "brandEntity")
    // brand - name of property in Car by which Car and Brand are connected
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<CarEntity> carEntities;

}
