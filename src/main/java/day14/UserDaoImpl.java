package day14;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    Session session = getSession();

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
    public List<User> readByEmail(String email) {
        Query query = session.createQuery("from User where email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }

    public Session getSession() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Tweet.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Follower.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }
}
