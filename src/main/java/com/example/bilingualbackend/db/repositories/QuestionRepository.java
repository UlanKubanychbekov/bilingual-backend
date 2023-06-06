package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}