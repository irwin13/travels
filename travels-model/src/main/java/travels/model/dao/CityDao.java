package travels.model.dao;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import travels.shared.entity.City;

import java.io.Serializable;
import java.util.List;

/**
 * Created by irwin on 11/22/14.
 */
public class CityDao extends AbstractDAO<City> {

    public CityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected City get(Serializable id) {
        return super.get(id);
    }

    public List<City> select(City filter) {
        List<City> list = null;
        Criteria criteria = criteria();
        return (list == null) ? Lists.<City>newArrayList() : list;
    }
}
