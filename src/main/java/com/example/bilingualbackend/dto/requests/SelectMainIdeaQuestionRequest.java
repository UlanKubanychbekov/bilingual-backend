package com.example.bilingualbackend.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SelectMainIdeaQuestionRequest extends QuestionMainRequest{
    private String title;
    private Integer duration;
    private String passage;
    private List<OptionSelectMainIdeaRequest> optionRequests;
    private Long testId;
    private boolean isActive;
}
