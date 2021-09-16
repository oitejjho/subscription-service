package com.demo.subscriptionservice.config;

import com.demo.subscriptionservice.exceptions.DuplicateRequestException;
import com.demo.subscriptionservice.exceptions.InvalidRequestException;
import com.demo.subscriptionservice.model.Response;
import com.demo.subscriptionservice.model.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.NoSuchElementException;

import static com.demo.subscriptionservice.constants.StatusConstants.HttpConstants.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@ResponseBody
public class ControllerErrorHandlingConfig extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LogManager.getLogger(ControllerErrorHandlingConfig.class);

    @ExceptionHandler(DuplicateRequestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response handleDuplicateRequestException(DuplicateRequestException ex) {
        LOG.info("error happened status error {}", ex.getMessage());
        return new Response<>(new Status(ex.getStatus()), null);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleInvalidRequestException(InvalidRequestException ex) {
        LOG.info("error happened status error {}", ex.getMessage());
        return new Response<>(new Status(ex.getStatus()), null);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNoSuchElementException(NoSuchElementException ex) {
        LOG.info("error happened status error {}", ex.getMessage());
        return new Response<>(new Status(NOT_FOUND), null);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            LOG.info("error happened status error {} {}", ex.getMessage(), ex);
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


}
