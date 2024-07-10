package com.elsaspeak.quiz.repository;

import com.elsaspeak.quiz.entity.QuizParticipation;
import com.elsaspeak.quiz.mapper.QuestionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<QuizParticipation, Long> {

    @Query(value = "select o.id as questionId, o.title as question, o.meta as meta from question o left join quiz_question qq on o.id = qq.question_id WHERE qq.quiz_id = :quizId", nativeQuery = true)
    List<QuestionMapping> fetchQuestion(@Param("quizId") Long quizId);

    QuizParticipation findBySessionId(String sessionId);
}
