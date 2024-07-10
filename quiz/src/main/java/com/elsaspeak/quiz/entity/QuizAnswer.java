package com.elsaspeak.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz_answer")
@Getter
@Setter
@Builder
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "quiz_id")
    Long quizId;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "question_id")
    Long questionId;
    @Column(name = "answer")
    String answer;
    @Column(name = "timer")
    Long timer;
    @Column(name = "session_id")
    String sessionId;
}
