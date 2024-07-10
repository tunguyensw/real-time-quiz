package com.elsaspeak.quiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuestionDto {
    Integer questionId;
    String question;
    Object options;
    Object config;
}
