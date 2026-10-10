package com.example.bffservice.model;

import lombok.Data;

@Data
public class OrderItemDto {
    private String productName;
    private int quantity;
    private double pricePerUnit;
}
