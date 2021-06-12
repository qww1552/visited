package kr.ac.jejunu.visited;

import kr.ac.jejunu.visited.entity.Card;
import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class VisitedApplicationTests {
    String author = "minsung";
    String password="1234";
    String message = "test";
    Double latitude = 1.0;
    Double longitude = 1.0;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Test
    public void get() {
        Card card = cardRepository.findById(1).get();
        System.out.println(card);
        assertThat(card.getAuthor(), is(author));
        assertThat(card.getPassword(),is(password));
        assertThat(card.getMessage(),is(message));
        assertThat(card.getLatitude(),is(latitude));
        assertThat(card.getLongitude(),is(longitude));
    }

    @Test
    public void insert() {
        Card card = Card.builder()
                .author(author)
                .password(password)
                .message(message)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        cardRepository.save(card);

        Card insertedCard = cardRepository.findById(card.getId()).get();

        assertThat(insertedCard.getAuthor(), is(author));
        assertThat(insertedCard.getPassword(),is(password));
        assertThat(insertedCard.getMessage(),is(message));
    }

    @Test
    public void findByDistance() {
        Double[] position = {33.449574399999996
                ,126.5565696};

        List<Card> byDistance = cardService.findByDistance(position);
        List<Card> all = cardRepository.findAll();

//        assertThat(all,is(byDistance));
        System.out.println(byDistance);
    }

}
