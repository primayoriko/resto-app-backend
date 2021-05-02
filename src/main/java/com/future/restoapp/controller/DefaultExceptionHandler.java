package com.future.restoapp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
//    public ResponseEntity<Object> handleConflict(MethodArgumentNotValidException ex) {
//        String bodyOfResponse = "This should be application specific";
////        return handleExceptionInternal(ex, bodyOfResponse,
////                new HttpHeaders(), HttpStatus.BAD_REQUEST, null);
//    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity.badRequest().body(null);
//    }
}