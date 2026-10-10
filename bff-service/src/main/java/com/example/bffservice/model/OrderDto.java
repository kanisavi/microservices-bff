package com.example.bffservice.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id", "userId", "totalAmount", "currency", "items"})
public class OrderDto {
    private Long id;
    private Long userId;
    private double totalAmount;
    private String currency;
    private List<OrderItemDto> items;
}
