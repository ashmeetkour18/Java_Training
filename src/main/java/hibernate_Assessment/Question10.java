package hibernate_Assessment;

import day10.Person;
import day10.PhoneNumber;
import day10.Utility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Question10 {
    public static void main(String[] args) {
        //insert();
        insertMultiple();
        //remove();
    }
    private static void remove() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store = session.get(Store.class, 1);
        session.remove(store);
        transaction.commit();
        session.close();
    }
    private static void insertMultiple(){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store=session.get(Store.class,2);
        ProductNew product=new ProductNew();
        product.setName("product3");
        product.setStore(store);
        session.persist(product);
        transaction.commit();
        session.close();
    }
    private static void insert() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Store store = new Store();
        store.setName("store2");
        store.setAddress("address2");
        ProductNew product = new ProductNew();
        product.setName("product2");
        product.setStore(store);
        session.persist(product);
        transaction.commit();
        session.close();
    }

    public static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(ProductNew.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

}