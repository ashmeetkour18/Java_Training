package hibernate_Assessment;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.StandardGenerator;

public class CustomId implements StandardGenerator {
    private final String DEFAULT_SEQUENCE_NAME = "my_seq";
    private static int id;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Session session2 = day10.Utility.getSession();
        Integer nextId = session2.createQuery("select max(id) from Product", Integer.class).getSingleResult();
        if (nextId == null) {
            nextId = 1;
            return nextId;
        }
        return (nextId.intValue() + 1);
    }
}





