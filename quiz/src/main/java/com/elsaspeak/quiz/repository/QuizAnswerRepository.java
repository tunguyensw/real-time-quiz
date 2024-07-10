package com.elsaspeak.quiz.repository;

import com.elsaspeak.quiz.entity.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
}
