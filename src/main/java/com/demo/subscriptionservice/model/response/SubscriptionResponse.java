package com.demo.subscriptionservice.model.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubscriptionResponse {

    private String subscriptionId;

    private String email;

    private String firstName;

    private String gender;

    private String dateOfBirth;

    private Boolean consentFlag;

    private String newsletterId;

    private Boolean activeFlag;

    private LocalDateTime created;

}
