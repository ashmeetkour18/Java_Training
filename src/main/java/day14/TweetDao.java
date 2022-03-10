package day14;

import java.util.List;


public interface TweetDao {
    void create(Tweet tweet);

    List<Tweet> readByEmail(String email);

    List<Tweet> readByUserId(Integer userid);
}
