package com.example.bilingualbackend.dto.responses;

import com.example.bilingualbackend.db.enums.QuestionType;
import com.example.bilingualbackend.dto.requests.question.OptionSelectMainIdeaRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private QuestionType questionType;
    private String title;
    private Integer duration;
    private String passage;
    private String correctAnswer;
    private List<OptionSelectMainIdeaRequest> optionRequests;
    private Long testId;
    private boolean isActive;
    private String statement;
    private Integer count;
    private String value;
}
