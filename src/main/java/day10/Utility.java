package day10;
import hibernate_Assessment.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

    public class Utility {
        public static Session getSession() {
            Configuration configuration = new Configuration();
            configuration.configure();
            //configuration.addAnnotatedClass(NewStudent.class);
            configuration.addAnnotatedClass(Person.class);
            configuration.addAnnotatedClass(PhoneNumber.class);
configuration.addAnnotatedClass(Airport.class);
configuration.addAnnotatedClass(Passenger.class);
configuration.addAnnotatedClass(Ticket.class);
configuration.addAnnotatedClass(Product.class);
configuration.addAnnotatedClass(Cat.class);
configuration.addAnnotatedClass(User.class);

            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            return session;
        }

    }
