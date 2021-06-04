package kr.ac.jejunu.visited;

import java.sql.*;

public class CardDao {
    public Card get(Integer id) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/visited?serverTimezone=UTC",
                "test","1234"
        );
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from card where id = ?"
        );
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Card card = Card.builder()
                .id(resultSet.getInt("id"))
                .author(resultSet.getString("author"))
                .password(resultSet.getString("password"))
                .message(resultSet.getString("message"))
                .latitude(resultSet.getDouble("latitude"))
                .longitude(resultSet.getDouble("longitude"))
                .build();

        return card;
    }
}
