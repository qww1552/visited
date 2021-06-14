package kr.ac.jejunu.visited.model.dto;

import kr.ac.jejunu.visited.model.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto {
    private Integer id;
    private String author;
    private String message;
    private Double latitude;
    private Double longitude;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.author = card.getAuthor();
        this.message = card.getMessage();
        this.latitude = card.getLatitude();
        this.longitude = card.getLongitude();
    }
}
