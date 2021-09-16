package com.demo.subscriptionservice.model.request;

import com.demo.subscriptionservice.annotations.IsDate;
import com.demo.subscriptionservice.annotations.IsEnum;
import com.demo.subscriptionservice.annotations.Required;
import com.demo.subscriptionservice.enums.Gender;
import com.demo.subscriptionservice.utils.DateTimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateSubscriptionRequest {

    @Required(exception = HttpConstants.EMAIL_IS_REQUIRED)
    private String email;

    private String firstName;

    @IsEnum(enumClass = Gender.class, exception = HttpConstants.GENDER_IS_INVALID)
    private String gender;

    @IsDate(format = DateTimeUtils.DATE_FORMAT_YYYY_MM_DD, exception = HttpConstants.DATE_OF_BIRTH_IS_INVALID)
    @Required(exception = HttpConstants.DATE_OF_BIRTH_IS_REQUIRED)
    private String dateOfBirth;

    @Required(exception = HttpConstants.CONSENT_FLAG_IS_REQUIRED)
    private Boolean consentFlag;

    @Required(exception = HttpConstants.NEWSLETTER_ID_IS_REQUIRED)
    private String newsletterId;


}
