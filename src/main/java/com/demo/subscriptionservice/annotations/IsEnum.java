package com.demo.subscriptionservice.annotations;


import com.demo.subscriptionservice.enums.BaseEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsEnumAnnotation.class})
public @interface IsEnum {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    HttpConstants exception() default HttpConstants.BAD_REQUEST;

    Class<? extends BaseEnum> enumClass();

}
