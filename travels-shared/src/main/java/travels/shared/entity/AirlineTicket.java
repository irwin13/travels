package travels.shared.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "airline_ticket")
@NamedQueries({
        @NamedQuery(name = "airlineTicket.getAll",
                query = "SELECT a FROM AirlineTicket a")
})
public class AirlineTicket extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "from_city")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "destination_city")
    private City destinationCity;

    @Column(name = "price")
    private int price;

    @Column(name = "landing_date")
    @Temporal(TemporalType.DATE)
    private Date landingDate;

    @Column(name = "landing_time")
    @Temporal(TemporalType.TIME)
    private Date landingTime;

    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "arrival_time")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(Date landingDate) {
        this.landingDate = landingDate;
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Date landingTime) {
        this.landingTime = landingTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
