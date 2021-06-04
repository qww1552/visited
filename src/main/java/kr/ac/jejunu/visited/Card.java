package kr.ac.jejunu.visited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Integer id;
    private String author;
    private String password;
    private String message;
    private Double latitude;
    private Double longitude;
}
