package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Option;
import com.example.bilingualbackend.db.entities.Question;
import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.enums.QuestionType;
import com.example.bilingualbackend.db.repositories.QuestionRepository;
import com.example.bilingualbackend.db.repositories.TestRepository;
import com.example.bilingualbackend.dto.requests.OptionRequest;
import com.example.bilingualbackend.dto.requests.QuestionRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import com.example.bilingualbackend.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public SimpleResponse saveSelectRealWords(QuestionRequest questionRequest) {
        Test test = testRepository.findById(questionRequest.getTestId()).orElseThrow(() ->
                new NotFoundException(
                        "Test with id: " + questionRequest.getTestId() + " not found!"
                )
        );

        if (questionRequest.getQuestionType().equals(QuestionType.SELECT_ENGLISH_WORD)) {
            List<Option> options = new ArrayList<>();
            if (questionRequest.getOptionRequests() != null) {
                for (OptionRequest o : questionRequest.getOptionRequests()) {
                    options.add(new Option(o));
                }
            }

            Question question = Question.builder()
                    .title(questionRequest.getTitle())
                    .questionType(QuestionType.SELECT_ENGLISH_WORD)
                    .duration(questionRequest.getDuration())
                    .options(options)
                    .build();

            for (Option o : options) {
                o.setQuestion(question);
            }

            test.getQuestions().add(question);
            question.setTest(test);
            questionRepository.save(question);

            return new SimpleResponse(
                    "Question with " + QuestionType.SELECT_ENGLISH_WORD + " type is save successfully!",
                    "Ok");

        } else if (questionRequest.getQuestionType().equals(QuestionType.HIGHLIGHT_THE_ANSWER)) {
            Question question = Question.builder()
                    .title(questionRequest.getTitle())
                    .questionType(QuestionType.HIGHLIGHT_THE_ANSWER)
                    .duration(questionRequest.getDuration())
                    .passage(questionRequest.getPassage())
                    .correctAnswer(questionRequest.getCorrectAnswer())
                    .build();

            test.getQuestions().add(question);
            question.setTest(test);
            questionRepository.save(question);

            return new SimpleResponse(
                    "Question with " + QuestionType.HIGHLIGHT_THE_ANSWER + " type is save successfully!",
                    "Ok");
        }

        return null;

    }
}
