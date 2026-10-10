package com.example.bffservice.controller;

import com.example.bffservice.model.UserProfileResponse;
import com.example.bffservice.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/site-bff")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/user/{userId}")
    public UserProfileResponse getUserProfile(@PathVariable Long userId) {
        return userProfileService.getUserProfile(userId);
    }
}
