package com.neethi.quizapp.controller;

import com.neethi.quizapp.model.Question;
import com.neethi.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{cat_name}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable(name = "cat_name") String category){
        return questionService.getQByCategory(category);
    }

    @PostMapping("save")
    public ResponseEntity<String>  addQuestions(@RequestBody Question question){
        return questionService.addQuest(question);

    }

    @PutMapping("update/{id}")
    public Question updateQuestion(@PathVariable Integer id, @RequestBody Question question){
        question.setId(id);
        questionService.updateQuestion(question);
        return question;
    }
    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
       return questionService.deleteQ(id);
    }

}
