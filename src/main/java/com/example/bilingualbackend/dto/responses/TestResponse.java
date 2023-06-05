package com.example.bilingualbackend.dto.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class TestResponse {
    private Long id;
    private String title;
    private String description;
    private boolean enable;
    private int duration;

    public TestResponse(Long id, String title, String description, boolean enable, int duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.enable = enable;
        this.duration = duration;
    }
}
