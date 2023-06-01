package com.example.bilingualbackend.db.repositories;

import com.example.bilingualbackend.db.entities.Option;
import com.example.bilingualbackend.dto.requests.OptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

}