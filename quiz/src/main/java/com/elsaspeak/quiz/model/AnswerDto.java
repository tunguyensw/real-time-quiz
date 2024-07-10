package com.elsaspeak.quiz.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerDto {
    Long questionId;
    String [] selectedOptions;
    Long  timer;
}
