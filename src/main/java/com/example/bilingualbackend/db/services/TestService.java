package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.services.repositories.TestRepository;
import com.example.bilingualbackend.dto.requests.auth.TestRequest;
import com.example.bilingualbackend.dto.responses.auth.SimpleResponse;
import com.example.bilingualbackend.dto.responses.auth.TestResponse;
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
                        new NotFoundException("no test with such an id:"));
        if (testRequest.getTitle() != null && !testRequest.getTitle().isBlank()) {
            test.setTitle(testRequest.getTitle());
        }
        if (testRequest.getDescription() != null && !testRequest.getDescription().isBlank()) {
            test.setDescription(testRequest.getDescription());
        }
        if (!testRequest.isEnable()) {
            test.setEnable(testRequest.isEnable());
        }
        if (testRequest.getDuration() != 0) {
            test.setDuration(testRequest.getDuration());
        }
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
