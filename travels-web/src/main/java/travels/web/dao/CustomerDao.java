package travels.web.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import travels.shared.entity.Customer;

/**
 * Created by irwin on 12/15/14.
 */
public class CustomerDao extends AbstractDAO<Customer> {

    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void update(Customer customer) {
        currentSession().merge(customer);
    }
}
