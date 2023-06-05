package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Option;
import com.example.bilingualbackend.db.entities.Question;
import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.enums.QuestionType;
import com.example.bilingualbackend.db.repositories.QuestionRepository;
import com.example.bilingualbackend.db.repositories.TestRepository;
import com.example.bilingualbackend.dto.requests.question.OptionSelectMainIdeaRequest;
import com.example.bilingualbackend.dto.requests.question.QuestionMainRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import com.example.bilingualbackend.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    private SimpleResponse saveSelectRealWord(QuestionMainRequest request) {
        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(
                        "Test with id: " + request.getTestId() + " not found!"
                )
        );

        List<Option> options = new ArrayList<>();
        if (request.getOptionRequests() != null) {
            for (OptionSelectMainIdeaRequest o : request.getOptionRequests()) {
                options.add(new Option(o));
            }
        }

        Question question = Question.builder()
                .title(request.getTitle())
                .questionType(QuestionType.SELECT_ENGLISH_WORD)
                .duration(request.getDuration())
                .options(options)
                .build();

        for (Option o : options) {
            o.setQuestion(question);
        }

        test.getQuestions().add(question);
        question.setTest(test);
        questionRepository.save(question);

        return new SimpleResponse(
                "Question with " + QuestionType.SELECT_ENGLISH_WORD + " type is save successfully!");

    }

    public SimpleResponse saveHighLightTheAnswer(QuestionMainRequest request) {

        Test test = testRepository.findById(request.getTestId()).orElseThrow(() ->
                new NotFoundException(
                        "Test with id: " + request.getTestId() + " not found!"
                )
        );

        Question question = Question.builder()
                .title(request.getTitle())
                .questionType(QuestionType.HIGHLIGHT_THE_ANSWER)
                .duration(request.getDuration())
                .passage(request.getPassage())
                .correctAnswer(request.getCorrectAnswer())
                .build();

        test.getQuestions().add(question);
        question.setTest(test);
        questionRepository.save(question);

        return SimpleResponse.builder()
                .message("Question with " + QuestionType.HIGHLIGHT_THE_ANSWER + " type is save successfully!")
                .build();
    }

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
                        .value(x.getValue())
                        .question(question)
                        .title(question.getQuestionType().name())
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
                .correctAnswer(request.getStatement())
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
            case SELECT_ENGLISH_WORD -> {
                return saveSelectRealWord(questionMainRequest);
            }
            case HIGHLIGHT_THE_ANSWER -> {
                return saveHighLightTheAnswer(questionMainRequest);
            }
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