package com.rahul.TriviaTrek.dao;

import com.rahul.TriviaTrek.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}