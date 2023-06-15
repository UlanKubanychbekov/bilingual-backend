package com.example.bilingualbackend.dto.responses;

import com.example.bilingualbackend.db.enums.QuestionType;
import lombok.*;


@Data
@Builder
@RequiredArgsConstructor
public class QuestionResponse {

    private String title;
    private QuestionType questionType;
    private int duration;
    private boolean enable;

    public QuestionResponse(String title, QuestionType questionType, int duration, boolean enable) {
        this.title = title;
        this.questionType = questionType;
        this.duration = duration;
        this.enable = enable;
    }

}
