package com.example.bilingualbackend.db.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private boolean enable;
    @NotNull
    private int duration;
    @OneToMany(mappedBy = "test", cascade = ALL)
    private List<Question> questions;
}