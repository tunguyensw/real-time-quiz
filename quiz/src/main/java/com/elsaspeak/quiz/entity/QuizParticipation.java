package com.elsaspeak.quiz.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_participation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizParticipation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "quiz_id")
    Long quizId;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "score")
    Double score;
    @Column(name = "start_quiz")
    LocalDateTime startQuiz;
    @Column(name = "end_quiz")
    LocalDateTime endQuiz;
    @Column(name = "session_id")
    String sessionId;
    @Column(name = "status")
    String status;
}
