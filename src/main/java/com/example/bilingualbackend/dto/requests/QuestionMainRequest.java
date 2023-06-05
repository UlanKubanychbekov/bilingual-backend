package com.example.bilingualbackend.dto.requests;

import com.example.bilingualbackend.db.enums.QuestionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionMainRequest {
    private QuestionType questionType;
}
