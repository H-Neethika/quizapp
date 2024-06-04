package com.neethi.quizapp.service;

import com.neethi.quizapp.model.Question;
import com.neethi.quizapp.dao.Questiondao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    Questiondao questiondao;
    public ResponseEntity<List<Question>> getAllQuestions() {
    try{
        return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
    }
    catch (Exception e){
        e.printStackTrace();
    }
    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity< List<Question>> getQByCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String>  addQuest(@RequestBody Question question) {
        questiondao.save(question);
        return new ResponseEntity<>("Question is successfully added",HttpStatus.CREATED) ;
    }

    public String deleteQ(Integer id) {
        questiondao.deleteById(id);
        return "successfully deleted";
    }

    public Question updateQuestion(Question question) {
      questiondao.save(question);
      return question;

    }
}
