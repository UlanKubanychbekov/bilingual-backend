package com.example.bilingualbackend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SimpleResponse {

    private String message;
    private String status;

}