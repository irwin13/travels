package traveldemo.view;

import io.dropwizard.views.View;
import traveldemo.hibernate.entity.AirlineTicket;
import traveldemo.hibernate.entity.City;

import java.util.List;

/**
 * Created by irwin on 11/16/14.
 */
public class HomePageView extends View {

    private final List<City> cityList;
    private final List<AirlineTicket> airlineTicketList;

    public HomePageView(List<City> cityList, List<AirlineTicket> airlineTicketList) {
        super("homePage.ftl");
        this.cityList = cityList;
        this.airlineTicketList = airlineTicketList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public List<AirlineTicket> getAirlineTicketList() {
        return airlineTicketList;
    }
}
