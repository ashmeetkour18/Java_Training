package hibernate_Assessment;
import day10.Utility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Question7 {
    public static void main(String[] args) {
        Session session = Utility.getSession();
        Transaction transaction = session.beginTransaction();
        Product product = new Product("product2", 1000);
        session.persist(product);
        transaction.commit();
        session.close();
    }
}
