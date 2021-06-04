package kr.ac.jejunu.visited;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CardTest {
    @Test
    public void get() throws SQLException, ClassNotFoundException {
        CardDao cardDao = new CardDao();
        Card card = cardDao.get(1);
        String author = "minsung";
        String password="1234";
        String message = "test";
        Double latitude = 1.0;
        Double longitude = 1.0;
        assertThat(card.getAuthor(), is(author));
        assertThat(card.getPassword(),is(password));
        assertThat(card.getMessage(),is(message));
        assertThat(card.getLatitude(),is(latitude));
        assertThat(card.getLongitude(),is(longitude));
    }
}
