package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}