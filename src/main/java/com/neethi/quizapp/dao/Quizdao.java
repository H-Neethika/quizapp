package com.neethi.quizapp.dao;

import com.neethi.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Quizdao extends JpaRepository<Quiz,Integer> {
}
