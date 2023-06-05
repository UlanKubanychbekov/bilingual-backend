package com.example.bilingualbackend.db.entities;

import com.example.bilingualbackend.db.enums.ContentType;
import com.example.bilingualbackend.db.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", sequenceName = "question_seq",allocationSize = 1, initialValue = 11)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;
    private int duration;
    private boolean enable;
    @ElementCollection()
    @MapKeyEnumerated(EnumType.STRING)
    private Map<ContentType, String> value;
    private Integer count;
    @Column(length = 10000)
    private String passage;
    @Column(length = 10000)
    private String correctAnswer;
    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<Option> options;
    @ManyToOne(cascade = {MERGE, DETACH})
    private Test test;
    @OneToMany(cascade = ALL, mappedBy = "question")
    private List<QuestionAnswer> questionAnswers;


}