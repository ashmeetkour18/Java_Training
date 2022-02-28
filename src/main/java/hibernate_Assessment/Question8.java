package hibernate_Assessment;

import day10.Utility;
import hql.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class Question8 {
    public static void main(String[] args) {
       // insertRecords();
        List<Object[]> users=maxCount();
        users.forEach(user -> System.out.println("userId - " + user[0]+"  count - "+user[1]));
    }
    private static List<Object[]> maxCount(){
        Session session = Utility.getSession();
        return( session.createQuery("select userid,count(message)  from User group by userid " +
                "ORDER BY count(message) DESC limit 1",Object[].class).list());
    }
    private static void insertRecords() {
        Session session = Utility.getSession();
        int[] userid=new int[]{1,2,3,4};
        Transaction transaction = session.beginTransaction();
        for(int i=0;i<50;i++){
            User user=new User();
            user.setUserid(userid[(int)(Math.random()*4)]);
         user.setMessage("message"+(i+1));
            session.persist(user);
        }
        transaction.commit();
        session.close();
    }

}
