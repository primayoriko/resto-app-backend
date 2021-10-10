package com.future.restoapp.controller;

import com.future.restoapp.model.dto.ErrorResponse;
import com.future.restoapp.model.entity.User;
import com.future.restoapp.model.exception.AccessPrivilegeNotEnoughException;
import com.future.restoapp.model.exception.BusinessLogicException;
import com.future.restoapp.model.security.UserPrincipal;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class BaseController{

    public static User getUser(Principal principal) throws Exception {
        if(principal instanceof UsernamePasswordAuthenticationToken
            && ((UsernamePasswordAuthenticationToken) principal).getPrincipal() instanceof UserPrincipal){
            User user = ((UserPrincipal) ((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getUser();
            if(user == null) throw new AuthenticationCredentialsNotFoundException("Account not found");
            return user;
        }
        throw new RuntimeException("mismatch principal type");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.add(error.getDefaultMessage());
        });

        return ErrorResponse.buildErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                errors.toString(),
                ""
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleTypeValidationExceptions(MethodArgumentTypeMismatchException ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                ""
        );
    }

    @ExceptionHandler(value = {
            NoSuchElementException.class,
            FileNotFoundException.class,
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNotFoundItemExceptions(Exception ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                ""
        );
    }

    @ExceptionHandler(value = {
            DataIntegrityViolationException.class,
            SQLIntegrityConstraintViolationException.class,
            BusinessLogicException.class
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity handleDataOrStateViolationExceptions(Exception ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.CONFLICT,
                "Data or state conflict occurred and can't be processed right now",
                ""
        );
    }

    @ExceptionHandler(value = {
            SQLDataException.class,
            SQLGrammarException.class,
    })
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleSQLDataAndGrammarExceptions(SQLException ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Something wrong in the input and can't be processed",
                ""
        );
    }

    @ExceptionHandler(SQLException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleSQLExceptions(SQLException ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Something wrong when processing data with the DB",
                ""
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handleIllegalArgumentExceptions(IllegalArgumentException ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ex.getMessage(),
                ""
        );
    }

    @ExceptionHandler(AccessPrivilegeNotEnoughException.class)
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity handleAccessPrivilegeException(AccessPrivilegeNotEnoughException ex){
        return ErrorResponse.buildErrorResponse(
                HttpStatus.FORBIDDEN,
                ex.getMessage(),
                ""
        );
    }

}
