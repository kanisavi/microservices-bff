package com.example.bffservice.exception;

import lombok.Getter;

@Getter
public class DownstreamServiceException extends RuntimeException {

    private final String serviceName;

    public DownstreamServiceException(String serviceName, String message) {
        super(message);
        this.serviceName = serviceName;
    }

}