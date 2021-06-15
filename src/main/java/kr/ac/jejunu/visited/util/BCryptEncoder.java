package kr.ac.jejunu.visited.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptEncoder implements PasswordEncoder{
    @Override
    public String encode(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt(12));
    }

    @Override
    public boolean checkPassword(String password, String encoded) {
        return BCrypt.checkpw(password, encoded);
    }
}
