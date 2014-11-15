package traveldemo.hibernate.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import traveldemo.hibernate.entity.Test1;

import java.util.List;

/**
 * Created by irwin on 11/16/14.
 */
public class Test1Dao extends AbstractDAO<Test1> {

    public Test1Dao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Test1> findAll() {
        return list(namedQuery("findAll"));
    }
}
