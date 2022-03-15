package twitterProject.dao;

import twitterProject.entity.Follower;

import java.util.List;

public interface FollowerDao {

    void create(Follower follower);

    List<Follower> readByEmail(String email);

    List<Follower> readById(Integer id);

    List<Follower> findAll(Integer id);
}
