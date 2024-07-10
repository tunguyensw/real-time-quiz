package com.elsaspeak.quiz.service;

import com.elsaspeak.quiz.entity.*;
import com.elsaspeak.quiz.enums.QuizStatus;
import com.elsaspeak.quiz.exception.InvalidRequestException;
import com.elsaspeak.quiz.exception.QuizNotFoundException;
import com.elsaspeak.quiz.handler.QuizEventHandler;
import com.elsaspeak.quiz.handler.QuizSubmittedEvent;
import com.elsaspeak.quiz.mapper.QuestionMapping;
import com.elsaspeak.quiz.mapper.SubmitQuizMapper;
import com.elsaspeak.quiz.model.*;
import com.elsaspeak.quiz.repository.*;
import com.elsaspeak.quiz.enums.PlayStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    QuizAnswerRepository answerRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubmitQuizMapper mapper;
    @Autowired
    QuizEventHandler eventHandler;

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public QuizInfoDto getQuizInfo(String quizNo) {
        Quiz quiz = quizRepository.findByQuizNo(quizNo).orElseThrow(() -> new QuizNotFoundException(quizNo));

        final Map<String, Object> config = readMeta(quiz.getMeta());
        Long quizTime = Optional.ofNullable(Long.parseLong(config.get("quizTime").toString())).orElse(0l);

        return QuizInfoDto.builder()
                .quizId(quiz.getId())
                .quizNo(quiz.getQuizNo())
                .title(quiz.getTitle())
                .quizTime(quizTime)
                .note(String.format("Time %s minutes", quizTime / 60000))
                .build();
    }

    @Override
    @Transactional
    public QuizSessionResponse joinQuiz(JoinQuizRequest request) {
        // validate quiz request
        if (StringUtils.isEmpty(request.getQuizNo()) || StringUtils.isEmpty(request.getUserName()))
            throw new InvalidRequestException("Invalid request");

        Quiz quiz = quizRepository.findByQuizNo(request.getQuizNo()).orElseThrow(() -> new QuizNotFoundException(request.getQuizNo()));

        if (QuizStatus.valueOf(quiz.getStatus()) != QuizStatus.ACTIVE)
            throw new InvalidRequestException("Quiz is not available");
        User user = userRepository.findByUserName(request.getUserName());

        // save to db
        String sessionId = UUID.randomUUID().toString();
        LocalDateTime startSession = LocalDateTime.now();
        QuizParticipation participation = QuizParticipation.builder()
                .quizId(quiz.getId())
                .userId(user.getUserId())
                .sessionId(sessionId)
                .startQuiz(startSession)
                .status(PlayStatus.PENDING.name())
                .build();

        sessionRepository.save(participation);

        // get questions of quiz
        List<QuestionMapping> questionMappings = sessionRepository.fetchQuestion(quiz.getId());

        List<QuestionDto> questions = new ArrayList<>();
        for (QuestionMapping mapping : questionMappings) {
            Map<String, Object> meta = readMeta(mapping.getMeta());

            questions.add(QuestionDto.builder().questionId(mapping.getQuestionId())
                    .question(mapping.getQuestion())
                    .options(meta.get("options"))
                    .config(meta.get("config"))
                    .build());
        }

        QuizSessionResponse response = QuizSessionResponse.builder()
                .sessionId(sessionId)
                .quizId(quiz.getId())
                .quizNo(quiz.getQuizNo())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .startTime(startSession)
                .questions(questions)
                .build();

        // publish event to kafka

        return response;
    }

    @Override
    @Transactional
    public SubmitQuizResponse submitQuiz(SubmitQuizRequest request) {
        // validate request
        if (request.getQuizId() == null || request.getUserId() == null)
            throw new InvalidRequestException("Invalid quiz id or user id");

        Quiz quiz = quizRepository.findById(request.getQuizId()).orElseThrow(() -> new QuizNotFoundException(request.getQuizId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new InvalidRequestException(String.format("Not found user: %s", request.getUserId())));

        QuizParticipation session = sessionRepository.findBySessionId(request.getSessionId());

        if (!CollectionUtils.isEmpty(request.getData())) {
            List<QuizAnswer> answers = new ArrayList<>();
            for (AnswerDto answered : request.getData()) {
                answers.add(QuizAnswer.builder()
                        .quizId(quiz.getId())
                        .userId(user.getUserId())
                        .questionId(answered.getQuestionId())
                        .answer(String.join("|", answered.getSelectedOptions()))
                        .sessionId(session.getSessionId())
                        .timer(answered.getTimer())
                        .build());
            }
            answerRepository.saveAll(answers);
        }
        // update status for quiz
        session.setStatus(PlayStatus.COMPLETED_ONE_TIME.name());
        sessionRepository.save(session);

        // store data to quiz answer
        SubmitQuizResponse response = SubmitQuizResponse.builder()
                .quizId(quiz.getId())
                .quizNo(quiz.getQuizNo())
                .quizTitle(quiz.getTitle())
                .sessionId(session.getSessionId())
                .endQuiz(LocalDateTime.now())
                .build();

        // publish event to kafka
        QuizSubmittedEvent event = mapper.toEvent(request);
        eventHandler.publish(event);
        return response;
    }
}
