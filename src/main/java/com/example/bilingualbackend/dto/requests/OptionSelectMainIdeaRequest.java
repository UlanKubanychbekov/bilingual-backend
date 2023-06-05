package com.example.bilingualbackend.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OptionSelectMainIdeaRequest {
    private String value;
    private boolean isCorrect;
}
