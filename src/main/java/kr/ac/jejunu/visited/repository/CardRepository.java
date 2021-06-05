package kr.ac.jejunu.visited.repository;

import kr.ac.jejunu.visited.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByAuthor(String author);
}
