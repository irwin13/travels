package travels.shared.entity;

import javax.persistence.*;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "airline")
@NamedQueries({
        @NamedQuery(name = "airline.getAll", query = "SELECT a FROM Airline a")
})
public class Airline extends BasicEntity {

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_code")
    private String airlineCode;

    @Column(name = "logo_img")
    private String logoImg;

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getLogoImg() {
        return logoImg;
    }

    public void setLogoImg(String logoImg) {
        this.logoImg = logoImg;
    }
}
