package com.elsaspeak.quiz.exception;

public class InvalidRequestException extends IllegalArgumentException {
    public InvalidRequestException(String msg) {
        super(msg);
    }
}
