package travels.model.dao;

import com.google.common.collect.Lists;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import travels.shared.entity.AirlineTicket;

import java.util.List;

/**
 * Created by irwin on 12/7/14.
 */
public class AirlineTicketDao extends AbstractDAO<AirlineTicket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineTicketDao.class);

    public AirlineTicketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<AirlineTicket> select(AirlineTicket filter) {
        List<AirlineTicket> list;
        LOGGER.debug("filter = {}", filter);

        Session session = currentSession();
        StringBuilder sql = new StringBuilder("SELECT a FROM AirlineTicket a WHERE 1=1 ");

        if (filter.getDestinationCity() != null) {
            sql.append(" AND a.destinationCity = :destinationCity ");
        }

        if (filter.getFromCity() != null) {
            sql.append(" AND a.fromCity = :fromCity ");
        }

        if (filter.getLandingDate() != null) {
            sql.append(" AND a.landingDate = :landingDate ");
        }

        if (filter.getArrivalDate() != null) {
            sql.append(" AND a.arrivalDate = :arrivalDate ");
        }

        Query query = session.createQuery(sql.toString());
        query.setProperties(filter);

        list = query.list();
        return (list == null) ? Lists.<AirlineTicket>newLinkedList() : list;

    }
}
