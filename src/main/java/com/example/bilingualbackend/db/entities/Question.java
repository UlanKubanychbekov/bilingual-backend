package com.example.bilingualbackend.db.entities;

import com.example.bilingualbackend.db.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "questions")
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", sequenceName = "question_seq",allocationSize = 1)
    private Long id;
    private String title;
    @Enumerated
    private QuestionType questionType;
    private int duration;
    private boolean enable;
    @ElementCollection
    private Map<String, String> content;
    private int numberOfReplays;
    private String statement;
    private String passage;
    @OneToMany(mappedBy = "question" ,cascade = ALL)
    private List<Option> options;
    @ManyToOne(cascade = {MERGE, DETACH})
    private Test test;
    @OneToMany(cascade = ALL, mappedBy = "question")
    private List<QuestionAnswer> questionAnswers;
}