package com.example.bilingualbackend.controllers;

import com.example.bilingualbackend.db.services.QuestionService;
import com.example.bilingualbackend.dto.requests.question.QuestionMainRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @MutationMapping(name = "saveQuestion")
    public SimpleResponse saveQuestion(@Argument QuestionMainRequest request){
        return questionService.saveQuestion(request);
    }
}