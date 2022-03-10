package day14;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FollowerDao {

    void create(Follower follower);

    List<Follower> readByEmail(String email);
}
