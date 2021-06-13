package kr.ac.jejunu.visited.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResponse {
    private Integer status;
    private String error;
    private String message;
    @Builder.Default
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

}
