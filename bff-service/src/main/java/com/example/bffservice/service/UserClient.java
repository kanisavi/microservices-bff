package com.example.bffservice.service;

import com.example.bffservice.exception.DownstreamServiceException;
import com.example.bffservice.exception.UserNotFoundException;
import com.example.bffservice.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public UserDto getUserById(Long userId) {
        String url = userServiceUrl + "/api/users/" + userId;
        try {
            ResponseEntity<UserDto> response = restTemplate.getForEntity(url, UserDto.class);
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            if (statusCode.value() == 404) {
                throw new UserNotFoundException("User with id=" + userId + " not found");
            }
            throw new DownstreamServiceException(
                    "user-service",
                    "Error calling user-service: HTTP " + statusCode.value()
            );
        } catch (Exception ex) {
            throw new DownstreamServiceException(
                    "user-service",
                    "Unknown error calling user-service: " + ex.getMessage()
            );
        }
    }
}
