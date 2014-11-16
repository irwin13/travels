package traveldemo.resources;

import io.dropwizard.hibernate.UnitOfWork;
import traveldemo.hibernate.dao.AirlineTicketDao;
import traveldemo.hibernate.dao.CityDao;
import traveldemo.hibernate.entity.AirlineTicket;
import traveldemo.view.HomePageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;

/**
 * Created by irwin on 11/16/14.
 */
@Path("/home")
public class HomePageResource {

    private final CityDao cityDao;
    private final AirlineTicketDao airlineTicketDao;

    public HomePageResource(CityDao cityDao, AirlineTicketDao airlineTicketDao) {
        this.cityDao = cityDao;
        this.airlineTicketDao = airlineTicketDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public HomePageView home() {
        return new HomePageView(cityDao.getAll(), new LinkedList<AirlineTicket>());
    }
}
