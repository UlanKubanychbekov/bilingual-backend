package com.example.bilingualbackend.controllers.graphqlControllers;

import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.services.TestService;
import com.example.bilingualbackend.dto.TestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
  private final TestService testService;

  @MutationMapping(name = "create")
    public Test create(@Argument TestDto testDto){
    return testService.createNewTest(testDto);
  }

  @QueryMapping()
  public List<Test> findAll(){
    return testService.getAllTests();
  }

  @QueryMapping(name = "findById")
  public Test findById(@Argument Long id){
    return testService.findById(id);
  }

  @MutationMapping(name = "update")
  public Test update(@Argument Long id,@Argument TestDto testDto){
    return testService.updateTest(id,testDto);
  }

  @MutationMapping(name = "delete")
  public void delete(@Argument Long id){
    testService.delete(id);
  }

}
