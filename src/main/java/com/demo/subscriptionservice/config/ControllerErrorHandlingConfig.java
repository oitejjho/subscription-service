package com.demo.subscriptionservice.config;

import com.demo.subscriptionservice.constants.StatusConstants;
import com.demo.subscriptionservice.controller.BaseController;
import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.model.Response;
import com.demo.subscriptionservice.model.Status;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants.INTERNAL_SERVER_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ResponseBody
public class ControllerErrorHandlingConfig extends ResponseEntityExceptionHandler{

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleInvalidRequestException(InvalidRequestException ex) {
        return new Response<>(new Status(ex.getStatus()), null);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


}
