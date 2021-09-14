package com.demo.subscriptionservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@Getter
@AllArgsConstructor
public class Status {

    private String code;

    private String message;

    public Status(HttpConstants httpConstantsExpect) {
        this.code = httpConstantsExpect.getCode();
        this.message = httpConstantsExpect.getDesc();
    }

}
