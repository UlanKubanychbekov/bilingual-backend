package com.example.bilingualbackend.db.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@Entity
@Table(name = "tests")
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq",allocationSize = 1)
    private Long id;
    private String title;
    private String description;
    private boolean enable;
    private int duration;
    @OneToMany(mappedBy = "test", cascade = ALL)
    private List<Question> questions;
}