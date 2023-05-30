package com.example.bilingualbackend.db.services.repositories;

import com.example.bilingualbackend.db.entities.Test;
import com.example.bilingualbackend.dto.responses.auth.TestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    @Query("select new com.example.bilingualbackend.dto.responses.auth.TestResponse(" +
            "t.id," +
            "t.title, " +
            "t.description," +
            "t.enable," +
            "t.duration)from Test t")
    List<TestResponse> findAllTests();


    @Query("select new com.example.bilingualbackend.dto.responses.auth.TestResponse(" +
            "t.id, t.title, t.description, t.enable, t.duration) " +
            "from Test t where t.id = :id")
    TestResponse findTestById(Long id);
}
