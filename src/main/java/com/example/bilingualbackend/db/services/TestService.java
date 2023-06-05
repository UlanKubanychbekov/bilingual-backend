package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.repositories.TestRepository;
import com.example.bilingualbackend.dto.requests.test.TestRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import com.example.bilingualbackend.dto.responses.TestResponse;
import com.example.bilingualbackend.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public List<TestResponse> getAllTests() {
        return testRepository.findAllTests();

    }

    public SimpleResponse createNewTest(TestRequest testRequest) {

        Test test = new Test();

        test.setTitle(testRequest.getTitle());
        test.setDescription(testRequest.getDescription());
        test.setDuration(testRequest.getDuration());
        test.setEnable(testRequest.isEnable());
        testRepository.save(test);
        return new SimpleResponse(String.format("Successfully created a new test with %s", test.getId()));
    }

    @Transactional
    public SimpleResponse updateTest(Long id, TestRequest testRequest) {
        Test test = testRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(String.format("No test with such an id: %s", id)));
        test.setTitle(testRequest.getTitle());
        test.setDescription(testRequest.getDescription());
        test.setEnable(testRequest.isEnable());
        test.setDuration(testRequest.getDuration());
        return new SimpleResponse(String.format("Updated test with id: %s", id));
    }

    public TestResponse findById(Long id) {
        return testRepository.findTestById(id);
    }

    public SimpleResponse delete(Long id) {
        testRepository.deleteById(id);
        return new SimpleResponse(String.format("Successfully deleted a test with id: %s", id));
    }
}
