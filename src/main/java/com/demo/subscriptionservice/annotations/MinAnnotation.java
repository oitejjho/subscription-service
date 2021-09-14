package com.demo.subscriptionservice.annotations;

import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@Component
public class MinAnnotation implements ConstraintValidator<Min, Object> {

    private HttpConstants exception;
    private long min;

    @Override
    public void initialize(Min constraintAnnotation) {
        this.exception = constraintAnnotation.exception();
        this.min = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value != null) {
            if (value instanceof Integer intValue) {
                if (intValue < min) {
                    throw new InvalidRequestException(exception);
                }
            } else if (value instanceof Long longValue) {
                if (longValue < min) {
                    throw new InvalidRequestException(exception);
                }
            }

        }
        return true;
    }

}
