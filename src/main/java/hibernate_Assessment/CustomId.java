package hibernate_Assessment;

import hql.Utility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.factory.spi.StandardGenerator;

public class CustomId implements StandardGenerator {
        private final String DEFAULT_SEQUENCE_NAME="my_seq";
        private static int id;

        @Override
        public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
            Session session2= day10.Utility.getSession();
            Long numberOfRecords = session2.createQuery("select count(name) from Product",Long.class).getSingleResult();
            //System.out.println(numberOfRecords);
            return (numberOfRecords+1);
        }

    }





