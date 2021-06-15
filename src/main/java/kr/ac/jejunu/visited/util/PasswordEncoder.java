package kr.ac.jejunu.visited.util;

public interface PasswordEncoder {
    String encode(String password);

    boolean checkPassword(String password, String encoded);
}
