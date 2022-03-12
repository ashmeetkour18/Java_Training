package facebook.dao;


import facebook.entity.Friend;

import java.util.List;

public interface FriendDao {
    List<Friend> readAll();

    void create(Friend friend);

    void createMultiple(int id, Friend friend);
}
