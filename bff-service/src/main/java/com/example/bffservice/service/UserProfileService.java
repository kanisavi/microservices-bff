package com.example.bffservice.service;

import com.example.bffservice.model.OrderDto;
import com.example.bffservice.model.UserDto;
import com.example.bffservice.model.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserClient userClient;
    private final OrderClient orderClient;

    public UserProfileResponse getUserProfile(Long userId) {
        UserDto user = userClient.getUserById(userId);
        List<OrderDto> orders = orderClient.getOrdersByUserId(userId);

        UserProfileResponse response = new UserProfileResponse();
        response.setUserId(user.getId());
        response.setFullName(user.getFullName());
        response.setDeliveryAddress(user.getDeliveryAddress());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setEmail(user.getEmail());
        response.setOrders(orders);

        return response;
    }
}
