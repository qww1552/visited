package kr.ac.jejunu.visited.service;

import kr.ac.jejunu.visited.model.entity.Card;
import kr.ac.jejunu.visited.repository.CardRepository;
import kr.ac.jejunu.visited.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.InputMismatchException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CardService {

    @Autowired
    private final EntityManager entityManager;
    @Autowired
    private final CardRepository cardRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public List<Card> findByDistance(Double[] position) {
        double distance = 0.01;
        double latitude = position[0];
        double longitude = position[1];
        String qlString = "select c FROM card as c " +
                "where c.latitude >= :latitude - :distance and c.latitude <= :latitude + :distance and c.longitude >= :longitude - :distance and c.longitude <= :longitude + :distance";
        TypedQuery<Card> query = entityManager.createQuery(qlString,Card.class);
        query.setParameter("latitude", latitude);
        query.setParameter("longitude", longitude);
        query.setParameter("distance", distance);
        return query.getResultList();
    }

    public Card save(Card card) {
        card.setPassword(passwordEncoder.encode(card.getPassword()));
        return cardRepository.save(card);
    }

    public void checkPassword(String password, String encodedPassword) {
        if (!passwordEncoder.checkPassword(password,encodedPassword)) {
            throw new InputMismatchException("비밀번호가 틀렸습니다.");
        }
    }
}
