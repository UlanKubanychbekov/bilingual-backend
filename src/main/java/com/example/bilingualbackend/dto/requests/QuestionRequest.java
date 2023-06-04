package com.example.bilingualbackend.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {

    private Long testId;
    private String title;
    private Integer duration;
    private String passage;
    private String correctAnswer;
    private List<OptionRequest> optionRequests;

}
