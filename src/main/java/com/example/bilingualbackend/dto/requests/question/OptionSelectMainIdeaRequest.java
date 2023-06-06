package com.example.bilingualbackend.dto.requests.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OptionSelectMainIdeaRequest {

    private String title;
    private String value;
    private boolean isCorrect;
}
