package hibernate_Assessment;

import Day8.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Question3 {
    public static void main(String[] args) {
        usingXml();
       usingAnnotation();
    }

    private static void usingAnnotation() {
        StudentEntity student=new StudentEntity("name1","1001");
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(StudentEntity.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
    }

    private static void usingXml() {
        NewStudent student=new NewStudent("name1","1001");
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addResource("newStudent.hbm.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
    }
    
}

