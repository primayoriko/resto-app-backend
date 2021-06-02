package com.future.restoapp.controller;

import com.future.restoapp.model.dto.core.ErrorResponse;
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
                "Body either not valid/malformed or field conversion from json error, please check again",
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

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request
//    ){
//        ErrorResponse message = new ErrorResponse(
//                status.value(),
//                status.getReasonPhrase(),
//                "Argument(s) passed is/are not valid",
//                ""
//        );
//
//        return ResponseEntity.badRequest().body(message);
//    }

//    @ExceptionHandler(Exception.class)
//    @Order(Ordered.LOWEST_PRECEDENCE)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<Object> handleAllUncaughtException(
//            RuntimeException exception,
//            WebRequest request
//    ){
//        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//
//        ErrorResponse message = new ErrorResponse(
//                status.value(),
//                status.getReasonPhrase(),
//                "Unknown unhandled error, please contact the developer",
//                ""
//        );
//
//        return ResponseEntity.badRequest().body(message);
//    }

}