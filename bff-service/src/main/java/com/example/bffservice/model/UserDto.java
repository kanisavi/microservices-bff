package com.example.bffservice.model;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String deliveryAddress;
    private String phoneNumber;
    private String email;
}
