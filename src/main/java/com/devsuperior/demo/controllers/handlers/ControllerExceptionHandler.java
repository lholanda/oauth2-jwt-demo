package com.devsuperior.demo.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.demo.dto.CustomError;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
// @ExceptionHandler - para capturar exceções específicas

    // metodo que capta ResourceNotFound.class 
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
       HttpStatus status = HttpStatus.NOT_FOUND;
       CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

       return ResponseEntity.status(status).body(err);
    }

    // metodo que capta ResourceNotFound.class 
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomError> userNameNotFound(UsernameNotFoundException e, HttpServletRequest request) {
       HttpStatus status = HttpStatus.NOT_FOUND;
       CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());

       return ResponseEntity.status(status).body(err);
    }
}
