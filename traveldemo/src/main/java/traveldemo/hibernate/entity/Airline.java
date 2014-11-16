package traveldemo.hibernate.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by irwin on 11/16/14.
 */
@Entity
@Table(name = "airline")
@NamedQueries({
        @NamedQuery(name = "airline.getAll", query = "SELECT a FROM Airline a")
})
public class Airline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "airline_name")
    private String airlineName;

    @Column(name = "airline_code")
    private String airlineCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airline airline = (Airline) o;

        if (id != airline.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
