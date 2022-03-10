package day14;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class FollowerDaoImpl implements FollowerDao {
    @Autowired
    private Session session;

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
}
