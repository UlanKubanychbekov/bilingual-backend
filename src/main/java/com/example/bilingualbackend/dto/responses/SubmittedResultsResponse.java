package com.example.bilingualbackend.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class SubmittedResultsResponse {
    private Long resultId;
    private String userFullName;
    private LocalDateTime dateOfSubmission;
    private String testName;
    private boolean resultStatus;
    private float finalScore;
}
