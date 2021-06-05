package kr.ac.jejunu.visited;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {
}
