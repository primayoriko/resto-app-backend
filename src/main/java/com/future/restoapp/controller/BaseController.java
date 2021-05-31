package com.future.restoapp.controller;

import com.future.restoapp.model.dto.ErrorResponse;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.model.security.UserPrincipal;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.security.Principal;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
public class BaseController {

    public static User getUser(Principal principal) throws Exception {
        if(principal instanceof UsernamePasswordAuthenticationToken
            && ((UsernamePasswordAuthenticationToken) principal).getPrincipal() instanceof UserPrincipal){
            return ((UserPrincipal) ((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getUser();
        }

        throw new RuntimeException("mismatch principal type");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleValidationExceptions(
            MethodArgumentNotValidException ex){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
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

        return ResponseEntity.unprocessableEntity().body(message);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleTypeValidationExceptions(
            MethodArgumentNotValidException ex){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                ""
        );

        return ResponseEntity.unprocessableEntity().body(message);
    }

    @ExceptionHandler(value = {
//            ResourceNotFoundException.class,
            NoSuchElementException.class,
            FileNotFoundException.class,
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNotFoundItemExceptions(
            NoSuchElementException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
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
    public ResponseEntity handleSQLIntegrityViolationExceptions(Exception ex){
        HttpStatus status = HttpStatus.CONFLICT;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Conflict in database occurred",
                ""
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(value = {
            SQLDataException.class,
            SQLGrammarException.class,
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleSQLDataAndGrammarExceptions(SQLException ex){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

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
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        ErrorResponse message = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                "Something wrong when processing the DB",
                ""
        );

        return ResponseEntity.unprocessableEntity().body(message);
    }

}
