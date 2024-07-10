package com.elsaspeak.quiz.handler;

import com.elsaspeak.quiz.model.AnswerDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class QuizSubmittedEvent {
    Long quizId;
    Long userId;
    List<AnswerDto> data;
}
