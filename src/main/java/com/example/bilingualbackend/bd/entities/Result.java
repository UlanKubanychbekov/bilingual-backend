package com.example.bilingualbackend.bd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_seq")
    @SequenceGenerator(name = "result_seq", sequenceName = "result_seq",allocationSize = 1)
    private Long id;
    private LocalDateTime dateOfSubmission;
    private boolean isEvaluated;
    private double score;
    private boolean isSeen;
    @ManyToOne(cascade = {REFRESH, DETACH, MERGE})
    private User user;
    @OneToOne(cascade = {REFRESH, DETACH, MERGE})
    private Test test;
    @OneToMany(cascade = {ALL})
    private List<QuestionAnswer> questionAnswers;
}