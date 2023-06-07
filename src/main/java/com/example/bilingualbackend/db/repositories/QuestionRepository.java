package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Question;
import com.example.bilingualbackend.dto.responses.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select  new com.example.bilingualbackend.dto.responses.QuestionResponse(" +
            "q.title," +
            "q.questionType," +
            "q.duration," +
            "q.enable) from Question  q where q.test.id=:id")
    List<QuestionResponse> findQuestionsByTestId(Long id);

}