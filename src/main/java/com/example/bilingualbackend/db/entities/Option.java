package com.example.bilingualbackend.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "options")
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "options_seq")
    @SequenceGenerator(name = "options_seq", sequenceName = "options_seq",allocationSize = 1)
    private Long id;
    private String title;
    private String value;
    private boolean isTrue;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Question question;

    public Option(String value, boolean isTrue) {
        this.value = value;
        this.isTrue = isTrue;
    }
}