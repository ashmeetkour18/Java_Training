package twitterProject.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitterProject.entity.User;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    Session session;
    @Override
    public List<User> readAll() {
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void create(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public List<Object[]> read() {
        return session.createQuery("select name,email,password from User", Object[].class).getResultList();
    }

    @Override
    public List readByEmail(String email) {
        Query query = session.createQuery("from User where email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public User findByEmail(String userEmail) {
        String SQL = "From User where email = '" + userEmail + "'";
        List<User> resultList = session.createQuery(SQL, User.class).getResultList();
        System.out.println(resultList + " this is the list");
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }


}
