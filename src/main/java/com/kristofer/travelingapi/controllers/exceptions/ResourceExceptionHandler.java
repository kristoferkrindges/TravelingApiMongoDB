package com.kristofer.travelingapi.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kristofer.travelingapi.services.exceptions.ObjectAlreadyExistsException;
import com.kristofer.travelingapi.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> 
    objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
    
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), 
        status.value(),"Not found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardError> 
    objectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request){
    
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(System.currentTimeMillis(), 
        status.value(),"Already Exists: ", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
