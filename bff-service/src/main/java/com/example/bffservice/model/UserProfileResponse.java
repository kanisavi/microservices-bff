package com.example.bffservice.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"userId", "fullName", "deliveryAddress", "phoneNumber", "email", "orders"})
public class UserProfileResponse {
    private Long userId;
    private String fullName;
    private String deliveryAddress;
    private String phoneNumber;
    private String email;
    private List<OrderDto> orders;
}
