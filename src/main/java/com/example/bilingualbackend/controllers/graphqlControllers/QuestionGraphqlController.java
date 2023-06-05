package com.example.bilingualbackend.controllers.graphqlControllers;

import com.example.bilingualbackend.db.services.QuestionService;
import com.example.bilingualbackend.dto.requests.QuestionRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/graphiql/questions")
@RequiredArgsConstructor
public class QuestionGraphqlController {

    private final QuestionService questionService;


    @MutationMapping(name = "saveQuestion")
    SimpleResponse saveQuestion(@Argument QuestionRequest questionRequest) {
        return questionService.saveSelectRealWords(questionRequest);
    }
}
