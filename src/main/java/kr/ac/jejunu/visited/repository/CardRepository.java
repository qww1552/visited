package kr.ac.jejunu.visited.repository;

import kr.ac.jejunu.visited.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByAuthor(String author);
}
