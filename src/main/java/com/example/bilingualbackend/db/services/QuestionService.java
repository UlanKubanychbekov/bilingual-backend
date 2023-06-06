package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Option;
import com.example.bilingualbackend.db.entities.Question;
import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.enums.ContentType;
import com.example.bilingualbackend.db.enums.QuestionType;
import com.example.bilingualbackend.db.repositories.QuestionRepository;
import com.example.bilingualbackend.db.repositories.TestRepository;
import com.example.bilingualbackend.dto.requests.question.QuestionMainRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import com.example.bilingualbackend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    private SimpleResponse saveSelectMainIdeaQuestion(QuestionMainRequest request) {
//      FIELDS:
//      String title;
//      Integer duration;
//      String passage;
//      List<OptionSelectMainIdeaRequest> optionRequests;
//      Long testId;
//      boolean isActive;

        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NotFoundException(String.format("Test with ID %s doesn't exist", request.getTestId())));

        Question question = Question.builder()
                .questionType(QuestionType.SELECT_THE_MAIN_IDEA)
                .duration(request.getDuration())
                .enable(request.isActive())
                .title(request.getTitle())
                .passage(request.getPassage())
                .test(test)
                .build();

        List<Option> options = request.getOptionRequests().stream()
                .map(x -> Option.builder()
                        .isTrue(x.isCorrect())
                        .question(question)
                        .title(x.getValue())
                        .build())
                .collect(Collectors.toList());

        question.setOptions(options);

        questionRepository.save(question);

        return SimpleResponse.builder()
                .message(String.format("Question with title '%s' successfully saved", request.getTitle()))
                .build();
    }

    public SimpleResponse saveRecordSayingStatement(QuestionMainRequest request) {
//      FIELDS:
//      String title;
//      String statement;
//      Integer duration;
//      Long testId;
//      Boolean isActive;

        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new NotFoundException(String.format("Test with ID %s doesn't exist", request.getTestId())));

        Question question = Question.builder()
                .questionType(QuestionType.RECORD_SAYING_STATEMENT)
                .title(request.getTitle())
                .value(Map.of(ContentType.TEXT, request.getStatement()))
                .duration(request.getDuration())
                .test(test)
                .enable(request.isActive())
                .build();
        questionRepository.save(question);

        return SimpleResponse.builder()
                .message(String.format("Question with title '%s' successfully saved", request.getTitle()))
                .build();
    }

    public SimpleResponse saveQuestion(QuestionMainRequest questionMainRequest) {
        switch (questionMainRequest.getQuestionType()) {
            case RECORD_SAYING_STATEMENT -> {
                return saveRecordSayingStatement(questionMainRequest);
            }
            case SELECT_THE_MAIN_IDEA -> {
                return saveSelectMainIdeaQuestion(questionMainRequest);
            }
            default -> {
                return SimpleResponse.builder()
                        .message("Something went wrong...")
                        .build();
            }
        }
    }
}