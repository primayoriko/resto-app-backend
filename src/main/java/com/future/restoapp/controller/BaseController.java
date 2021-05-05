package com.future.restoapp.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            body.put("status", 400);
            body.put("error", "BAD REQUEST");
            body.put("message", errors);

            return ResponseEntity.badRequest().body(body);
        }

}
