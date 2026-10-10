package com.example.orderservice.repository;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final List<Order> orders = List.of(
            new Order(1L, 1L, 1500.0, "RUB", List.of(
                    new OrderItem("Книга", 1, 500.0),
                    new OrderItem("Ноутбук", 1, 1000.0))
            ),
            new Order(2L, 1L, 300.0, "RUB", List.of(
                    new OrderItem("Мышка", 1, 300.0))
            ),
            new Order(3L, 2L, 200.0, "RUB", List.of(
                    new OrderItem("Кружка", 2, 100.0))
            )
    );

    public List<Order> findByUserId(Long userId) {
        return orders.stream()
                .filter(o -> o.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
