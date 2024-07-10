package com.elsaspeak.quiz.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class JoinQuizRequest {
    @NonNull
    String quizNo;
    @NonNull
    String userName;
}
