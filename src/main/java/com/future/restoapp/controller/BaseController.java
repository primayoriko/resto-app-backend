package com.future.restoapp.controller;

import com.future.restoapp.model.dto.ErrorResponse;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
            errors.add(error.getDefaultMessage());
        });

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
            NoSuchElementException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                ""
        );

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(FileNotFoundException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleFileNotFoundExceptions(
            FileNotFoundException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "File requested was not found",
                ""
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(value = {
            DataIntegrityViolationException.class,
            SQLIntegrityConstraintViolationException.class
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleIntegrityViolationExceptions(Exception ex){
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Conflict in database occurred",
                ""
        );

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = {
            SQLDataException.class,
            SQLGrammarException.class,
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleDataAndGrammarExceptions(SQLException ex){
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Something wrong in the input and can't be processed",
                ""
        );

        return ResponseEntity.unprocessableEntity().body(message);
    }

    @ExceptionHandler(SQLException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleSQLExceptions(SQLException ex){
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Something wrong when processing the DB",
                ""
        );

        return ResponseEntity.unprocessableEntity().body(message);
    }

}
