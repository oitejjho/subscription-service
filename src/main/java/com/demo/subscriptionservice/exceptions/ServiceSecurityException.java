package com.demo.subscriptionservice.exceptions;

import lombok.Getter;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;


@Getter
public class ServiceSecurityException extends RuntimeException {

    private final HttpConstants status;

    public ServiceSecurityException(HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}
