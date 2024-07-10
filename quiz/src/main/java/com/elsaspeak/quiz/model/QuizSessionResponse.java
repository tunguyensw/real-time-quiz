package com.elsaspeak.quiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class QuizSessionResponse {
    String sessionId;
    Long quizId;
    String quizNo;
    Long userId;
    String userName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    double score;
    List<QuestionDto> questions;
}
