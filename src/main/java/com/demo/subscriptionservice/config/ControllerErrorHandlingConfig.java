package com.demo.subscriptionservice.config;

import com.demo.subscriptionservice.controller.BaseController;
import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.model.Response;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants;

@RestControllerAdvice
public class ControllerErrorHandlingConfig implements BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();
        return badRequest(HttpConstants.BAD_REQUEST, fieldError.getDefaultMessage(), response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException ex, HttpServletResponse response) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        return badRequest(HttpConstants.BAD_REQUEST, violation.getMessage(), response);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public Response handleInvalidRequestException(InvalidRequestException ex, HttpServletResponse response) {
        String validationMessage = ex.getMessage();
        return unauthorizedRequest(HttpConstants.BAD_CREDENTIALS, validationMessage, response);
    }

    /*@ExceptionHandler(BadCredentialsException.class)
    public Response handleBadCredentialsException(BadCredentialsException ex, HttpServletResponse response) {
        String validationMessage = ex.getMessage();
        return badRequest(HttpConstants.BAD_REQUEST, validationMessage, response);
    }*/

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpServletResponse response) {
        return badRequest(HttpConstants.BAD_REQUEST, ex.getMessage(), response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletResponse response) {
        String errorMessage = ex.getName() + " should be type " + ex.getRequiredType().getName();
        return badRequest(HttpConstants.BAD_REQUEST, errorMessage, response);
    }

}
