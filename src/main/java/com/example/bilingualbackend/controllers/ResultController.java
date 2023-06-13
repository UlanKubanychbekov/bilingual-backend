package com.example.bilingualbackend.controllers;

import com.example.bilingualbackend.db.services.ResultService;
import com.example.bilingualbackend.dto.responses.SubmittedResultsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @QueryMapping(name = "getAllSubmittedResults")
    public List<SubmittedResultsResponse> getAll(@Argument Long userId){
        return resultService.getAllSubmittedResults(userId);
    }
}
