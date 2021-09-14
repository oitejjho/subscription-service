package com.demo.subscriptionservice.annotations;


import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@Component
public class IsDateAnnotation implements ConstraintValidator<IsDate, String> {

    private HttpConstants exception;

    private String format;

    @Override
    public void initialize(IsDate constraintAnnotation) {
        this.exception = constraintAnnotation.exception();
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isNotBlank(date)) {
            if (DateTimeUtils.parse(date, format).isPresent()) {
                return true;
            }
            throw new InvalidRequestException(exception);
        }
        return true;
    }

}
