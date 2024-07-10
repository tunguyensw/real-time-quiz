package com.elsaspeak.quiz.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SubmitQuizRequest {
    Long quizId;
    Long userId;
    String sessionId;
    List<AnswerDto> data;
}
