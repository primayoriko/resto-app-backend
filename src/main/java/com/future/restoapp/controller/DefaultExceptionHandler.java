package com.future.restoapp.controller;

import com.future.restoapp.model.dto.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@Order(Ordered.HIGHEST_PRECEDENCE + 10)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    public DefaultExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        return ErrorResponse.buildErrorResponse(
                status,
                "Body Not Valid/Malformed",
                ""
        );
    }

    @Override
    protected ResponseEntity handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        return ErrorResponse.buildErrorResponse(
                status,
                "Specified request method not supported",
                ""
        );
    }

}