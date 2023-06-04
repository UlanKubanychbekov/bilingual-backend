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
<<<<<<< HEAD
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq",allocationSize = 1, initialValue = 3)
=======
    @SequenceGenerator(name = "test_seq", sequenceName = "test_seq", allocationSize = 1)
>>>>>>> f2efd256cb6f34def1cc975133a3f26eba188ca8
    private Long id;

    private String title;

    private String description;

    private boolean enable;

    private int duration;
    @OneToMany(mappedBy = "test", cascade = ALL)
    private List<Question> questions;
}