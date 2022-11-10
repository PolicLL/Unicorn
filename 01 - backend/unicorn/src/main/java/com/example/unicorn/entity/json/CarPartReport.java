package com.example.unicorn.entity.json;

import lombok.Data;

@Data
public class CarPartReport {
    private String brandName;
    private String carName;
    private int count = -1;
}
