package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}