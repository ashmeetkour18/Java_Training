package twitterProject.dao;

import twitterProject.entity.User;

import java.util.List;


public interface UserDao {
    List<User> readAll();

    void create(User user);

    List<Object[]> read();

    List<User> readByEmail(String email);

    User findByEmail(String userEmail);
}
