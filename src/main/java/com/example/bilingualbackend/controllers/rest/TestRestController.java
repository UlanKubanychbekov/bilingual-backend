package com.example.bilingualbackend.controllers.rest;

import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.services.TestService;
import com.example.bilingualbackend.dto.TestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tests")
public class TestRestController {
    private final TestService testService;

    @PostMapping("/create")
    public Test save(@RequestBody @Valid TestDto testDto){
        return testService.createNewTest(testDto);
    }

    @GetMapping()
    public List<Test> findAll(){
        return testService.getAllTests();
    }

    @GetMapping("/find/{id}")
    public Test findById(@PathVariable Long id){
        return testService.findById(id);
    }

    @PostMapping("/update/{id}")
    public Test update(@PathVariable Long id, @RequestBody @Valid TestDto testDto){
        return testService.updateTest(id,testDto);
   }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        testService.delete(id);
    }

}
