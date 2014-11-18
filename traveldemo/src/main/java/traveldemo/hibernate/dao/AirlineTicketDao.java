package traveldemo.hibernate.dao;

import com.google.common.collect.Lists;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import traveldemo.hibernate.entity.AirlineTicket;

import java.util.List;

/**
 * Created by irwin on 11/16/14.
 */
public class AirlineTicketDao extends AbstractDAO<AirlineTicket> {

    public AirlineTicketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<AirlineTicket> getAll() {
        return list(namedQuery("airlineTicket.getAll"));
    }

    public List<AirlineTicket> select(AirlineTicket filter) {
        List<AirlineTicket> list;
        Session session = currentSession();
        StringBuilder sql = new StringBuilder("SELECT a FROM AirlineTicket a WHERE 1=1");

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
