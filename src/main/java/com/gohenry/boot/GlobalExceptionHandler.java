package com.gohenry.boot;

import com.gohenry.boot.domain.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidations(ConstraintViolationException exception) {
        return ResponseEntity.badRequest()
                             .body(ErrorMessage.builder()
                                               .errorMsg(exception.getMessage())
                                               .generatedAt(LocalDateTime.now())
                                               .status(HttpStatus.BAD_REQUEST.value())
                                               .errors(exception.getClass().getSimpleName())
                                               .build());
    }
}
