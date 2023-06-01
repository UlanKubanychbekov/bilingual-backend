package com.example.bilingualbackend.dto.requests;

import com.example.bilingualbackend.db.enums.QuestionType;
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

    private String title;
    private QuestionType questionType;
    private int duration;
    private List<OptionRequest> optionRequests;
}
