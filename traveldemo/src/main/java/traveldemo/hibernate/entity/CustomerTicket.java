package traveldemo.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "customer_ticket")
@NamedQueries({
        @NamedQuery(name = "customerTicket.getAll",
                query = "SELECT c FROM CustomerTicket c"),
        @NamedQuery(name = "customerTicket.filterHomePage",
                query = "SELECT c FROM CustomerTicket c WHERE c.customerName = :customerName ")
})
public class CustomerTicket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @ManyToOne
    @JoinColumn(name = "airline_ticket")
    private AirlineTicket airlineTicket;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public AirlineTicket getAirlineTicket() {
        return airlineTicket;
    }

    public void setAirlineTicket(AirlineTicket airlineTicket) {
        this.airlineTicket = airlineTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerTicket that = (CustomerTicket) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
