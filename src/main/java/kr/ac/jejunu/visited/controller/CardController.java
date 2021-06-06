package kr.ac.jejunu.visited.controller;

import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
@CrossOrigin(value = "http://localhost:5000/", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CardController {
    @Autowired
    private final CardRepository cardRepository;

    @GetMapping
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/{cardId}")
    public Card getOneCard(@PathVariable Integer cardId) {
        return cardRepository.findById(cardId).get();
    }

    @PostMapping
    public Card addCard(@RequestBody Map<String,String> newCard) {
        Card card = Card.builder()
                .author(newCard.get("author"))
                .password(newCard.get("password"))
                .message(newCard.get("message"))
                .latitude(Double.valueOf(newCard.get("latitude")))
                .longitude(Double.valueOf(newCard.get("longitude")))
                .build();
        Card save = cardRepository.save(card);
        return save;
    }

    @PutMapping("/{cardId}")
    public Card modifyCard(@PathVariable Integer cardId, @RequestBody Map<String,String> update) {
        // 널일 경우 처리 필요

        Card card = Card.builder()
                .id(cardId)
                .author(update.get("author"))
                .password(update.get("password"))
                .message(update.get("message"))
                .latitude(Double.valueOf(update.get("latitude")))
                .longitude(Double.valueOf(update.get("longitude")))
                .build();

        return cardRepository.save(card);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable Integer cardId) {
        cardRepository.deleteById(cardId);
    }

}
