package com.elsaspeak.quiz.service;

import com.elsaspeak.quiz.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public interface QuizService {
     void error(String message);
     QuizInfoDto getQuizInfo(String quizNo);
     QuizSessionResponse joinQuiz(JoinQuizRequest request);
     SubmitQuizResponse submitQuiz(SubmitQuizRequest request);

     default Map<String, Object> readMeta(String meta) {
          ObjectMapper mapper = new ObjectMapper();
          Map<String, Object> metaData = new HashMap<>();
          try {
               metaData = mapper.readValue(meta, HashMap.class);
          } catch (JsonProcessingException e) {
               error("Not able read quiz configuration: Quiz -> Meta");
          }

          return metaData;
     }
}
