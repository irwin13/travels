import travels.shared.entity.Airline;

/**
 * Created by irwin on 11/22/14.
 */
public class TestThis {

    public static void main(String[] args) {
        Airline airline = new Airline();
        airline.setId("id airline");
        airline.setAirlineName("name airline");
        airline.setAirlineCode("code");
        System.out.println(airline);
    }
}
