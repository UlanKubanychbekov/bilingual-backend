package com.example.bilingualbackend.bd.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_seq")
    @SequenceGenerator(name = "option_seq", sequenceName = "option_seq",allocationSize = 1)
    private Long id;
    private String title;
    private String value;
    private boolean isTrue;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Question question;
}