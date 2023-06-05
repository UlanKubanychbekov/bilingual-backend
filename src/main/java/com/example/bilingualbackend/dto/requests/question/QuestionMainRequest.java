package com.example.bilingualbackend.dto.requests.question;

import com.example.bilingualbackend.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionMainRequest {
    private QuestionType questionType;
    private String title;
    private Integer duration;
    private String passage;
    private List<OptionSelectMainIdeaRequest> optionRequests;
    private Long testId;
    private boolean isActive;
    private String statement;
}