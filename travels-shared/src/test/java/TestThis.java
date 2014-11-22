import com.fasterxml.jackson.databind.ObjectMapper;
import travels.shared.entity.Airline;

import java.util.Date;
import java.util.Map;

/**
 * Created by irwin on 11/22/14.
 */
public class TestThis {

    public static void main(String[] args) {
        beanToMap();
    }

    private static void printThis() {
        Airline airline = new Airline();
        airline.setId("id airline");
        airline.setAirlineName("name airline");
        airline.setAirlineCode("code");
        System.out.println(airline);
    }

    private static void beanToMap() {
        Airline airline = new Airline();
        airline.setId("id airline");
        airline.setAirlineName("name airline");
        airline.setAirlineCode("code");
        airline.setLastUpdateDate(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, ?> objectMap = objectMapper.convertValue(airline, Map.class);

        Airline a = objectMapper.convertValue(objectMap, Airline.class);
        System.out.println(a);
    }
}
