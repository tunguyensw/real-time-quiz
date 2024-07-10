package com.elsaspeak.quiz.handler;

import com.elsaspeak.quiz.exception.InvalidRequestException;
import com.elsaspeak.quiz.exception.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {QuizNotFoundException.class, InvalidRequestException.class})
    public ResponseEntity<ErrorResponse> handleInvalidDataException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
