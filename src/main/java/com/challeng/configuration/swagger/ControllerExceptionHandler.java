package com.challeng.configuration.swagger;

import com.challeng.exception.SessionCosedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest400(MethodArgumentNotValidException ex){

        var list = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(list.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(SessionCosedException.class)
    public ResponseEntity sessioclosed(MethodArgumentNotValidException ex){

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public record ErrorValidationData(String field, String message){

        public ErrorValidationData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
