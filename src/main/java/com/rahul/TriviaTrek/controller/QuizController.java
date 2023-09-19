package com.rahul.TriviaTrek.controller;

import com.rahul.TriviaTrek.model.Question;
import com.rahul.TriviaTrek.model.QuestionWrapper;
import com.rahul.TriviaTrek.model.Quiz;
import com.rahul.TriviaTrek.model.Response;
import com.rahul.TriviaTrek.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// controller for quiz
@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("allQuizes")
    public ResponseEntity<List<Quiz>> getAllQuizes(){
        return quizService.getAllQuizes();
    }

    @GetMapping("get/{id}")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}