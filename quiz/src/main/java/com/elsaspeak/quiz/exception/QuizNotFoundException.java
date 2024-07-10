package com.elsaspeak.quiz.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(final String quizNo) {
        super(String.format("Not found quiz: %s", quizNo));
    }

    public QuizNotFoundException(final Long quizId) {
        super(String.format("Not found quiz: %s", quizId));
    }

}
