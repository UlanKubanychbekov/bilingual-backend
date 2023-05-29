package com.example.bilingualbackend.db.services.repositories;

import com.example.bilingualbackend.db.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test,Long> {


}
