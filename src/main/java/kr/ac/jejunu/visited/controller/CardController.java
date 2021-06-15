package kr.ac.jejunu.visited.controller;

import kr.ac.jejunu.visited.model.dto.CardResponseDto;
import kr.ac.jejunu.visited.model.dto.CardRequestDto;
import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.model.entity.Card;
import kr.ac.jejunu.visited.service.CardService;
import kr.ac.jejunu.visited.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
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
    public ResponseEntity getAllCards(@Param("latitude") Double latitude, @Param("longitude") Double longitude) {
        Double[] position = {latitude, longitude};
        List<CardResponseDto> cards = new LinkedList<>();

        List<Card> byDistance = cardService.findByDistance(position);
        for (Card card : byDistance) {
            cards.add(new CardResponseDto(card));
        }

        return new ResponseEntity(cards, HttpStatus.OK);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity getOneCard(@PathVariable Integer cardId) {
        CardResponseDto card = new CardResponseDto(
                cardRepository.findById(cardId).orElseThrow(() ->
                        new NoSuchElementException("card not found"))
        );

        return new ResponseEntity(card, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCard(@RequestBody @Valid CardRequestDto newCard) {
        CardResponseDto save = new CardResponseDto(cardService.save(newCard.convertToEntity()));
        return new ResponseEntity(save, HttpStatus.CREATED);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity updateCard(@PathVariable Integer cardId, @RequestBody @Valid CardRequestDto update) {
        Card cardById = cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        cardService.checkPassword(update.getPassword(), cardById.getPassword());

        update.setId(cardId);
        CardResponseDto updated = new CardResponseDto(cardService.save(update.convertToEntity()));

        return new ResponseEntity(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity deleteCard(@PathVariable Integer cardId, @RequestBody String password) {
        Card cardById = cardRepository.findById(cardId).orElseThrow(() ->
                new NoSuchElementException("card not found")
        );
        cardService.checkPassword(password,cardById.getPassword());

        cardRepository.deleteById(cardId);

        return new ResponseEntity(cardById,HttpStatus.OK);
    }


}
