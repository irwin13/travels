package travels.model.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import travels.shared.entity.City;

import java.util.List;

/**
 * Created by irwin on 11/22/14.
 */
public class CityDao extends AbstractDAO<City> {

    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<City> getAll() {
        return criteria().list();
    }

    public List<City> getByName(String name) {
        return criteria().add(Restrictions.eq("cityName", name)).list();
    }

    public City getByCode(String code) {
        return (City) criteria().add(Restrictions.eq("cityCode", code)).uniqueResult();
    }

}
