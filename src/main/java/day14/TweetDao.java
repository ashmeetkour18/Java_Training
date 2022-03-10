package day14;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TweetDao {
    void create(Tweet tweet);

    List<Tweet> readByEmail(String email);

    List<Tweet> readByUserId(Integer userid);
}
