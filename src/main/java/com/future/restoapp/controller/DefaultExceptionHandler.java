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

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    public DefaultExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Body Not Valid/Malformed",
                ""
        );

        return ResponseEntity.status(status).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Specified request method not supported",
                ""
        );

        return ResponseEntity.status(status).body(message);
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