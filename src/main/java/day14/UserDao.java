package day14;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    List<User> readAll();

    void create(User user);

    List<Object[]> read();

    List<User> readByEmail(String email);
}
