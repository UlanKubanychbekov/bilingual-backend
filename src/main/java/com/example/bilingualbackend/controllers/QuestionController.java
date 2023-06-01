package com.example.bilingualbackend.controllers;

import com.example.bilingualbackend.db.services.QuestionService;
import com.example.bilingualbackend.dto.requests.QuestionRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/save-select-real-word")
    public SimpleResponse saveSelectRealWordQuestion(@RequestBody QuestionRequest request) {
        return questionService.saveSelectRealEnglishWords(request);
    }
}
