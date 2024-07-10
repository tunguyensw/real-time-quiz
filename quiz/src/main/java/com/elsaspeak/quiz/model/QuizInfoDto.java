package com.elsaspeak.quiz.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QuizInfoDto {
    Long quizId;
    String quizNo;
    String title;
    Long quizTime;
    String note;
}
