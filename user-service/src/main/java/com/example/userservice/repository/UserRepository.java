package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = List.of(
            new User(1L, "Красная Маргарита Сергеевна", "Москва, ул. Пушкина, д. 1", "+7 900 111-00-11", "rita@example.com"),
            new User(2L, "Питерский Пётр Павлович", "Санкт-Петербург, Невский пр., д. 10", "+7 911 222-22-21", "peter@example.com")
    );

    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
}
