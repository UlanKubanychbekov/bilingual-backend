package com.example.bilingualbackend.dto.requests.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestRequest {
    private String title;
    private String description;
    private boolean enable;
    private int duration;
}
