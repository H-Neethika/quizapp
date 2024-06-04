package com.neethi.quizapp.service;

import com.neethi.quizapp.dao.Questiondao;
import com.neethi.quizapp.dao.Quizdao;
import com.neethi.quizapp.model.Question;
import com.neethi.quizapp.model.QuestionWrapper;
import com.neethi.quizapp.model.Quiz;
import com.neethi.quizapp.model.Response;
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
    Quizdao quizdao;

    @Autowired
    Questiondao questiondao;
    public ResponseEntity<String> createQ(String category, int numQ, String title) {

        List<Question> questions=questiondao.findRandomQuestionByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizdao.save(quiz);

        return  new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Optional<Quiz> quiz=quizdao.findById(id);
        List<Question> questionsFromDB=quiz.get().getQuestion();
        List<QuestionWrapper> questionsForUser=new ArrayList<>();

        for(Question q :questionsFromDB){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
      Quiz quiz=quizdao.findById(id).get();
      List<Question> questions=quiz.getQuestion();
      int i=0;
      int  right=0;
      for(Response r :responses){
          if(r.getResponse().equals(questions.get(i).getRightAnswer()))
          {
              right++;
          }
          i++;
      }
     return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
