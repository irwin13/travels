package traveldemo.hibernate.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
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

    public List<AirlineTicket> filterHomePage(AirlineTicket filter) {
        Query query = namedQuery("airlineTicket.filterHomePage");
        query.setProperties(filter);
        return query.list();
    }
}
