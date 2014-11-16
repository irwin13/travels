package traveldemo.hibernate.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import traveldemo.hibernate.entity.City;

import java.util.List;

/**
 * Created by irwin on 11/16/14.
 */
public class CityDao extends AbstractDAO<City> {

    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<City> getAll() {
        return list(namedQuery("city.getAll"));
    }
}
