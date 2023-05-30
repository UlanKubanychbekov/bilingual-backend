package com.example.bilingualbackend.controllers.rest;

import com.example.bilingualbackend.db.services.TestService;
import com.example.bilingualbackend.dto.requests.auth.TestRequest;
import com.example.bilingualbackend.dto.responses.auth.SimpleResponse;
import com.example.bilingualbackend.dto.responses.auth.TestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestRestController {
    private final TestService testService;

    @PostMapping("/create")
    public SimpleResponse save(@RequestBody @Valid TestRequest testRequest) {
        return testService.createNewTest(testRequest);
    }

    @GetMapping()
    public List<TestResponse> findAll() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public TestResponse findById(@PathVariable Long id) {
        return testService.findById(id);
    }

    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody @Valid TestRequest testRequest) {
        return testService.updateTest(id, testRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        testService.delete(id);
    }

}
