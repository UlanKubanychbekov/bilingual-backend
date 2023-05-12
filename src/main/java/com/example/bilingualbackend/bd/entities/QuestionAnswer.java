package com.example.bilingualbackend.bd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_answer_seq")
    @SequenceGenerator(name = "question_answer_seq", sequenceName = "question_answer_seq",allocationSize = 1)
    private Long id;
    private double score;
    private LocalTime fixedTime;
    private int duration;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private Question question;
    @ManyToMany(cascade = {DETACH, REFRESH, MERGE})
    private List<Option> selectedOptions;
}