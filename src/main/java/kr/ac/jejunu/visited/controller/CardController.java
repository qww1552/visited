package kr.ac.jejunu.visited.controller;

import kr.ac.jejunu.visited.api.CardDto;
import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.entity.Card;
import kr.ac.jejunu.visited.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("cards")
@CrossOrigin(value = "http://localhost:5000/", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CardController {
    @Autowired
    private final CardRepository cardRepository;

    @Autowired
    private final CardService cardService;

    @GetMapping
    public ResponseEntity getAllCards(@Param("latitude")Double latitude, @Param("longitude") Double longitude) {
        Double[] position = {latitude, longitude};
        List<CardDto> cards = new LinkedList<>();
        List<Card> byDistance = cardService.findByDistance(position);
        for (Card card : byDistance) {
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
        CardDto save = new CardDto(cardRepository.save(newCard));
        return new ResponseEntity(save, HttpStatus.CREATED);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity updateCard(@PathVariable Integer cardId, @RequestBody Card update) {
        Card cardById = cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        checkPassword(cardById.getPassword(),update.getPassword());
        update.setId(cardId);
        CardDto updated = new CardDto(cardRepository.save(update));
        return new ResponseEntity(updated, HttpStatus.OK);
    }

    private void checkPassword(String password, String incomingPassword) {
        if (!incomingPassword.equals(password)) {
            throw new InputMismatchException("비밀번호가 틀렸습니다.");
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity deleteCard(@PathVariable Integer cardId, @RequestBody String password) {
        Card cardById = cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        checkPassword(cardById.getPassword(),password);
        cardRepository.deleteById(cardId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity handleInputMismatchException(Exception e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
    }
}
