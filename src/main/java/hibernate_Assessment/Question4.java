package hibernate_Assessment;

import Day7.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Question4 {
    /*
    C - create <--created a table and inserted data into it
    R - read <-- fetch the data from the table
    U - update <--update the data in the table
    D - delete <--delete any row from the table
     */
    public static void main(String[] args) {
        //insertData();
       // readData();
        //updateData();
        deleteData();

    }
    private static void insertData() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        PersonAssessment person =new PersonAssessment(2,"name2");
        session.persist(person);
        transaction.commit();
        session.close();
    }

    private static void readData() {
        Session session = getSession();
        List<PersonAssessment> personList = session.createQuery("from PersonAssessment",PersonAssessment.class).getResultList();
        System.out.println(personList);
    }

    private static void updateData(){
        Session session=getSession();
        Transaction transaction= session.beginTransaction();
        PersonAssessment person =session.get(PersonAssessment.class,1);
        person.setName("name-updated");
        session.persist(person);
        transaction.commit();
        session.close();
    }

    private static void deleteData() {
        Session session = getSession();
        Transaction transaction=session.beginTransaction();
        PersonAssessment person=new PersonAssessment();
        person.setId(2);
        session.remove(person);
        transaction.commit();
        session.close();
    }

    private static Session getSession(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(PersonAssessment.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

}
