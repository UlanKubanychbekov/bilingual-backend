package com.example.bilingualbackend.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class TestDto {

    private Long id;
    private String title;
    private String description;
    private boolean enable;
    private int duration;
}
