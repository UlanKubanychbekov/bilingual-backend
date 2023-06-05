package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

}