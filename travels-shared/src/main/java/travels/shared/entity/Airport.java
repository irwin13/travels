package travels.shared.entity;

import javax.persistence.*;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "airport")
@NamedQueries({
        @NamedQuery(name = "airport.getAll", query = "SELECT a FROM Airport a")
})
public class Airport extends BasicEntity {

    @Column(name = "airport_name")
    private String airportName;

    @Column(name = "airport_code")
    private String airportCode;

    @Column(name = "airport_address")
    private String airportAddress;

    @ManyToOne
    @JoinColumn(name = "city")
    private City city;

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportAddress() {
        return airportAddress;
    }

    public void setAirportAddress(String airportAddress) {
        this.airportAddress = airportAddress;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
