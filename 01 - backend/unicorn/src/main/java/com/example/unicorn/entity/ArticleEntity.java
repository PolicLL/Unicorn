package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "name")
    private String name;

    @Column(name = "base_price")
    private double basePrice;
    @OneToOne(cascade =  CascadeType.MERGE)
    @JsonIgnoreProperties("articleEntity")
    @JoinColumn(name = "car_part_serial_number" , nullable = false)
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CarPartEntity carPartEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "discount_id")
    @JsonIgnoreProperties("articleEntities")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private DiscountEntity discountEntity;


}
