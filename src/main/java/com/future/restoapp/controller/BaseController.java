package com.future.restoapp.controller;

import com.future.restoapp.model.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@CrossOrigin
public class BaseController {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
            errors.add(error.getDefaultMessage());
        });

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                errors.toString(),
                ""
        );

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoSuchElementExceptions(
            MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                ""
        );

        return ResponseEntity.badRequest().body(message);
    }

}
