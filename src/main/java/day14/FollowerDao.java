package day14;

import java.util.List;

public interface FollowerDao {

    void create(Follower follower);

    List<Follower> readByEmail(String email);
}
