package com.elsaspeak.quiz.inbound;

import com.elsaspeak.quiz.model.*;
import com.elsaspeak.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    QuizService quizService;

    @GetMapping("")
    public QuizInfoDto getMessages(@RequestParam(name = "quiz_no") String quizNo) {
        return quizService.getQuizInfo(quizNo);
    }

    @PostMapping("/join")
    public QuizSessionResponse getMessages(@RequestBody JoinQuizRequest request) {
        return quizService.joinQuiz(request);
    }

    @PostMapping("/submit")
    public SubmitQuizResponse getMessages(@RequestBody SubmitQuizRequest request) {
        return quizService.submitQuiz(request);
    }
}
