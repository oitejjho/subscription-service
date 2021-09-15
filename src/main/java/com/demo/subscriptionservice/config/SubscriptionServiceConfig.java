package com.demo.subscriptionservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "subscription-service")
public class SubscriptionServiceConfig {

    @NotEmpty
    private String apiKey;

    @NotEmpty
    private String correlationIdKey;

    @NotEmpty
    private String serviceRole;

    @NotEmpty
    private String serviceUsername;

}
