package com.example.bffservice.service;

import com.example.bffservice.exception.DownstreamServiceException;
import com.example.bffservice.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderClient {

    private final RestTemplate restTemplate;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public List<OrderDto> getOrdersByUserId(Long userId) {
        String url = orderServiceUrl + "/api/orders/by-user/" + userId;

        try {
            ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<OrderDto>>() {
                    }
            );
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            HttpStatusCode statusCode = ex.getStatusCode();
            if (statusCode.value() == 404) {
                return Collections.emptyList();
            }
            throw new DownstreamServiceException(
                    "order-service",
                    "Error calling order-service: HTTP " + statusCode.value()
            );
        } catch (Exception ex) {
            throw new DownstreamServiceException(
                    "order-service",
                    "Unknown error calling order-service: " + ex.getMessage()
            );
        }
    }
}
