package com.example.bilingualbackend.db.services;

import com.example.bilingualbackend.dto.responses.SubmittedResultsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final JdbcTemplate jdbcTemplate;

    public List<SubmittedResultsResponse> getAllSubmittedResults(Long userId) {
        String sql = """
                SELECT r.id as id,
                CONCAT(u.first_name, ' ', u.last_name) as user_full_name,
                r.date_of_submission as date_of_submission,
                t.title as title,
                r.is_evaluated as status,
                r.score as score
                FROM results r
                JOIN users u ON r.user_id = u.id 
                JOIN tests t on r.test_id = t.id
                CASE 
                """;

        if (userId != null) sql += " WHERE u.id = " + userId;

        return jdbcTemplate.query(sql, (resultSet, i) ->
                new SubmittedResultsResponse(
                        resultSet.getLong("id"),
                        resultSet.getString("user_full_name"),
                        resultSet.getObject("date_of_submission", LocalDateTime.class),
                        resultSet.getString("title"),
                        resultSet.getBoolean("status"),
                        resultSet.getFloat("score")
                )
        );
    }
}
