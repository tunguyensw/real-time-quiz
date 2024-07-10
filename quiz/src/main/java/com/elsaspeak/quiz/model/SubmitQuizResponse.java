package com.elsaspeak.quiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SubmitQuizResponse {
    Long quizId;
    String quizNo;
    String quizTitle;
    String sessionId;
    LocalDateTime endQuiz;
}
