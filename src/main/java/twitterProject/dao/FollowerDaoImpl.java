package twitterProject.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitterProject.entity.Follower;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class FollowerDaoImpl implements FollowerDao {
    @Autowired
    Session session;

    @Override
    public void create(Follower follower) {
        session.beginTransaction();
        session.persist(follower);
        session.getTransaction().commit();
    }

    @Override
    public List<Follower> readByEmail(String email) {
        Query query = session.createQuery("from Follower where email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public List<Follower> readById(Integer id) {
        Query query = session.createQuery("from Follower where user_id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Follower> findAll(Integer userId) {
        String SQL = "from Follower where user_id = " + userId;
        List<Follower> resultList = session.createQuery(SQL, Follower.class).getResultList();
        if (resultList.size() > 0) {
            return resultList;
        }
        return new ArrayList<>();
    }
}
