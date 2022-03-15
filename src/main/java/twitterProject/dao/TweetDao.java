package twitterProject.dao;

import twitterProject.entity.Tweet;

import java.util.List;


public interface TweetDao {
    void create(Tweet tweet);

    List<Tweet> readByEmail(String email);

    List<Tweet> readByUserId(Integer userid);
}
