package com.example.unicorn.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "discount")
public class DiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =
            "discount_id")
    private Long discountId;

    @Column(name = "discount_percentage")
    private int discountPercentage;

    @Column(name = "start_date")
    private Date startCreated;

    @Column(name = "end_date")
    private Date endCreated;

    @OneToMany(cascade = CascadeType.PERSIST , mappedBy = "discountEntity")
    @JsonIgnoreProperties("discountEntity")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // sale - name of property in Article by which Article and Sale are connected
    private List<ArticleEntity> articleEntities;
}


