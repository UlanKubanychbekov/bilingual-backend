package com.example.bilingualbackend.controllers;

import com.example.bilingualbackend.db.services.QuestionService;
import com.example.bilingualbackend.dto.requests.QuestionRequest;
import com.example.bilingualbackend.dto.requests.question.RecordSayingStatementQuestionRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphiql/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;


    @MutationMapping(name = "saveQuestion")
    SimpleResponse saveQuestion(@Argument QuestionRequest questionRequest) {
        return questionService.saveQuestion(questionRequest);
    }

    @MutationMapping(name = "saveQuestion")
    public SimpleResponse saveQuestion(@Argument RecordSayingStatementQuestionRequest request){
        return questionService.saveQuestionSyimykMethod(request);
    }
}
