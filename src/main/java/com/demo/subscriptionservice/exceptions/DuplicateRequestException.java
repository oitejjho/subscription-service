package com.demo.subscriptionservice.exceptions;

import lombok.Getter;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;


@Getter
public class DuplicateRequestException extends RuntimeException {

    private final HttpConstants status;

    public DuplicateRequestException(HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}
