package kr.ac.jejunu.visited.api;

import kr.ac.jejunu.visited.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Integer id;
    private String author;
    private String message;
    private Double latitude;
    private Double longitude;

    public CardDto(Card card) {
        this.id = card.getId();
        this.author = card.getAuthor();
        this.message = card.getMessage();
        this.latitude = card.getLatitude();
        this.longitude = card.getLongitude();
    }
}
