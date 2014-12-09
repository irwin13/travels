package travels.web.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import travels.shared.entity.CustomerTicket;

/**
 * Created by irwin on 12/10/14.
 */
public class CustomerTicketDao extends AbstractDAO<CustomerTicket> {

    public CustomerTicketDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public CustomerTicket insert(CustomerTicket customerTicket) {
        return persist(customerTicket);
    }

    public CustomerTicket getById(String id) {
        return get(id);
    }

}
