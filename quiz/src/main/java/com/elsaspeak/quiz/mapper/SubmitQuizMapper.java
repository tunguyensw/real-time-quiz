package com.elsaspeak.quiz.mapper;
import com.elsaspeak.quiz.handler.QuizSubmittedEvent;
import com.elsaspeak.quiz.model.SubmitQuizRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface SubmitQuizMapper {
    @Mapping(source = "quizId", target = "quizId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "data", target = "data")
    QuizSubmittedEvent toEvent(SubmitQuizRequest request);
}
