package com.example.bilingualbackend.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RecordSayingStatementQuestionRequest extends QuestionMainRequest{
    @NotNull(message = "The title must not be empty.")
    private String title;
    @NotNull(message = "The statement must not be empty.")
    private String statement;
    @NotNull(message = "The duration must not be empty.")
    @Positive(message = "Duration can not be negative")
    private Integer duration;
    @NotNull(message = "The Test id must not be empty.")
    @Positive(message = "Test id can not be negative")
    private Long testId;
    @NotNull(message = "The isActive id must not be empty.")
    private Boolean isActive;
}
