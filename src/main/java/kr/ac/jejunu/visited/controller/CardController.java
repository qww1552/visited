package kr.ac.jejunu.visited.controller;

import kr.ac.jejunu.visited.api.CardDto;
import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
@CrossOrigin(value = "http://localhost:5000/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CardController {
    @Autowired
    private final CardRepository cardRepository;

    @GetMapping
    public ResponseEntity getAllCards(@RequestHeader HttpHeaders headers) {
        System.out.println(headers);
        List<CardDto> cards = new LinkedList<>();
        for (Card card : cardRepository.findAll()) {
            cards.add(new CardDto(card));
        }
        return new ResponseEntity(cards, HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity getOneCard(@PathVariable Integer cardId) {
        CardDto card = new CardDto(
                cardRepository.findById(cardId).orElseThrow(() ->
                        new NoSuchElementException("card not found"))
        );
        return new ResponseEntity(card, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCard(@RequestBody Card newCard) {
        System.out.println(newCard);
        CardDto save = new CardDto(cardRepository.save(newCard));
        return new ResponseEntity(save, HttpStatus.CREATED);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity updateCard(@PathVariable Integer cardId, @RequestBody Card update) {
        // 널일 경우 처리 필요
        cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        Card card = Card.builder()
                .id(cardId)
                .author(update.getAuthor())
                .password(update.getPassword())
                .message(update.getMessage())
                .latitude(update.getLatitude())
                .longitude(update.getLongitude())
                .build();

        CardDto updated = new CardDto(cardRepository.save(card));
        return new ResponseEntity(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity deleteCard(@PathVariable Integer cardId) {
        cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        cardRepository.deleteById(cardId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
