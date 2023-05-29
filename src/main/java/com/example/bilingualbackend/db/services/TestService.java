package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.db.entities.Question;
import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.db.services.repositories.TestRepository;
import com.example.bilingualbackend.dto.TestDto;
import com.example.bilingualbackend.exceptions.BadRequestException;
import com.example.bilingualbackend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTests(){
    return testRepository.findAll().stream().toList();
    }

    public Test createNewTest(TestDto testDto) {
        Test test = new Test();
        boolean exists = testRepository.findAll()
                        .stream().anyMatch(test1 -> Objects.equals(test1.getId(), testDto.getId()));
        if(!exists) {
            test.setId(testDto.getId());
            test.setTitle(testDto.getTitle());
            test.setDescription(testDto.getDescription());
            test.setDuration(testDto.getDuration());
            test.setEnable(testDto.isEnable());
            return testRepository.save(test);
        }else {
            throw new NullPointerException("testDto is null");
        }

    }

    public Test updateTest(Long id, TestDto dto) {
        Test test = findById(id);
                if(dto.getTitle() != null && !dto.getTitle().isBlank()) {
                    test.setTitle(dto.getTitle());
                }
                if(dto.getDescription()!=null && !dto.getDescription().isBlank()) {
                    test.setDescription(dto.getDescription());
                }
                if(!dto.isEnable()) {
                    test.setEnable(dto.isEnable());
                }
                if(dto.getDuration()!=0) {
                    test.setDuration(dto.getDuration());
                }
        return testRepository.save(test);
    }

    public Test findById(Long id) {
        return testRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("test with id = %s does not exists", id)
                ));
    }

    public void delete(Long id){
        boolean exists = testRepository.existsById(id);
        if(!exists){
            throw new BadRequestException(String.format("test with id = %s does not exists", id));
        }
      testRepository.deleteById(id);
    }
}
