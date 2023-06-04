package com.example.bilingualbackend.controllers.restControllers;

import com.example.bilingualbackend.db.services.QuestionService;
import com.example.bilingualbackend.dto.requests.QuestionRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/save-select-real-word")
    public SimpleResponse saveSelectRealWordQuestion(@RequestBody QuestionRequest request) {
        return questionService.saveSelectRealWords(request);
    }

    @PostMapping("/save-highlight-the-answer")
    public SimpleResponse saveHighlightTheAnswer(@RequestBody QuestionRequest questionRequest) {
        return questionService.saveHighLightTheAnswer(questionRequest);
    }
}
