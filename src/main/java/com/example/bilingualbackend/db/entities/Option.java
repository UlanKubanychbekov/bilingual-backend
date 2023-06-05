package com.example.bilingualbackend.db.entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "options")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "options_seq")
    @SequenceGenerator(name = "options_seq", sequenceName = "options_seq",allocationSize = 1, initialValue = 13)
    private Long id;
    private String title;
    private String value;
    private Boolean isTrue;
    @ManyToOne(cascade = {MERGE, REFRESH, DETACH})
    private Question question;

    public Option(OptionRequest request) {
        this.value = request.getValue();
        this.isTrue = request.getIsTrue();
    }
}