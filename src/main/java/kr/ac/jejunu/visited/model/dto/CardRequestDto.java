package kr.ac.jejunu.visited.model.dto;

import kr.ac.jejunu.visited.model.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {

    private Integer id;
    @NotBlank(message = "작성자는 공백일 수 없습니다.")
    private String author;
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    private String password;
    private String message;
    @NotNull(message = "좌표값은 null일 수 없습니다.")
    private Double latitude;
    @NotNull(message = "좌표값은 null일 수 없습니다.")
    private Double longitude;

    public CardRequestDto(Card card) {
        this.id = card.getId();
        this.author = card.getAuthor();
        this.password = card.getPassword();
        this.message = card.getMessage();
        this.latitude = card.getLatitude();
        this.longitude = card.getLongitude();
    }

    public Card convertToEntity() {
        return Card.builder()
                .id(this.id)
                .author(this.author)
                .password(this.password)
                .message(this.message)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .build();
    }
}
