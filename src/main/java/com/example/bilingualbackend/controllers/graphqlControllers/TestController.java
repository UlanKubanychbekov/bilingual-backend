package com.example.bilingualbackend.controllers.graphqlControllers;

import com.example.bilingualbackend.db.services.TestService;
import com.example.bilingualbackend.dto.requests.TestRequest;
import com.example.bilingualbackend.dto.responses.SimpleResponse;
import com.example.bilingualbackend.dto.responses.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;

    @MutationMapping(name = "create")
    public SimpleResponse create(@Argument TestRequest testRequest) {

        return testService.createNewTest(testRequest);
    }

    @QueryMapping()
    public List<TestResponse> findAll() {
        return testService.getAllTests();
    }

    @QueryMapping(name = "findById")
    public TestResponse findById(@Argument Long id) {
        return testService.findById(id);
    }

    @MutationMapping(name = "update")
    public SimpleResponse update(@Argument Long id, @Argument TestRequest testRequest) {
        return testService.updateTest(id, testRequest);
    }

    @MutationMapping(name = "delete")
    public SimpleResponse delete(@Argument Long id) {
        return testService.delete(id);
    }

}
