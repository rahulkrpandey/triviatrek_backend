package com.rahul.TriviaTrek.service;

import com.rahul.TriviaTrek.dao.QuestionDao;
import com.rahul.TriviaTrek.dao.QuizDao;
import com.rahul.TriviaTrek.model.Question;
import com.rahul.TriviaTrek.model.QuestionWrapper;
import com.rahul.TriviaTrek.model.Quiz;
import com.rahul.TriviaTrek.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions;
            if (category.equals("all")) {
                questions = questionDao.findRandomQuestions(numQ);
            } else {
                questions = questionDao.findRandomQuestionsByCategory(category, numQ);
            }

            if (questions.size() != numQ) {
                return new ResponseEntity<>("Quiz could not created!", HttpStatus.BAD_REQUEST);
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Quiz could not created!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for(Question q : questionsFromDB){
                QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getDescription(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionsForUser.add(qw);
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            int i = 0;
            for(Response response : responses){
                if(response.getResponse().equals(questions.get(i).getAnswer())) {
                    right++;
                }

                i++;
            }
            return new ResponseEntity<>(right, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Quiz>> getAllQuizes() {
        try {
            return new ResponseEntity<> (quizDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<> (new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}