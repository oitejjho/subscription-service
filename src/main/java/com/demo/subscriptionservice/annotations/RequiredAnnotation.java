package com.demo.subscriptionservice.annotations;

import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@Component
public class RequiredAnnotation implements ConstraintValidator<Required, Object> {

    private HttpConstants exception;

    @Override
    public void initialize(Required constraintAnnotation) {
        this.exception = constraintAnnotation.exception();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) {
            throw new InvalidRequestException(exception);
        } else if (o instanceof String && StringUtils.isBlank((String) o)) {
            throw new InvalidRequestException(exception);
        }
        return true;
    }

}
