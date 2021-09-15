package com.demo.subscriptionservice.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "subscribed_user")
public class SubscribedUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
