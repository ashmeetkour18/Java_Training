package day14;

import java.sql.Timestamp;

class Tweet {
    private String tweet;
    private Integer userId;
    private Timestamp timestamp;

    public Tweet(String tweet, Integer userId, Timestamp timestamp) {
        this.tweet = tweet;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public Tweet(String tweet, Integer userId) {
        this.tweet = tweet;
        this.userId = userId;
    }

    public Tweet() {
    }

    public Tweet(String tweet) {
        this.tweet = tweet;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp
                                     timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Tweets{" +
                "tweet='" + tweet + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}