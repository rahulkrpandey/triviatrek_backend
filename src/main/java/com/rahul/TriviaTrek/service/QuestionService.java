package com.rahul.TriviaTrek.service;

import com.rahul.TriviaTrek.model.Question;
import com.rahul.TriviaTrek.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Question is added successfully!",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Question could not be added!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question question) {
        try {
            Optional<Question> questionFromDBOptional = questionDao.findById(id);
            if (questionFromDBOptional.isEmpty()) {
                return new ResponseEntity<>("Question not found!", HttpStatus.NOT_FOUND);
            }

            Question questionFromDB = questionFromDBOptional.get();
            if (question.getAnswer() != null) {
                questionFromDB.setAnswer(question.getAnswer());
            }

            if (question.getOption1() != null) {
                questionFromDB.setOption1(question.getOption1());
            }

            if (question.getOption2() != null) {
                questionFromDB.setOption2(question.getOption2());
            }

            if (question.getOption3() != null) {
                questionFromDB.setOption3(question.getOption3());
            }

            if (question.getOption4() != null) {
                questionFromDB.setOption4(question.getOption4());
            }

            if (question.getDescription() != null) {
                questionFromDB.setDescription(question.getDescription());
            }

            if (question.getCategory() != null) {
                questionFromDB.setCategory(question.getCategory());
            }

            if (question.getDifficulty() != null) {
                questionFromDB.setDifficulty(question.getDifficulty());
            }

            System.out.println(question);
            System.out.println(questionFromDB);
            questionDao.save(questionFromDB);

            return new ResponseEntity<>("Question is updated Successfully!",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Question could not be updated!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            Optional<Question> questionFromDBOptional = questionDao.findById(id);
            if (questionFromDBOptional.isEmpty()) {
                return new ResponseEntity<>("Question not found!", HttpStatus.NOT_FOUND);
            }

            questionDao.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("success",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
