package travels.shared.entity;

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
public class CustomerTicket extends BasicEntity {

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "airline_ticket")
    private AirlineTicket airlineTicket;

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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
