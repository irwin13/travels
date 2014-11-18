package traveldemo.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.joda.time.DateTime;
import traveldemo.hibernate.dao.AirlineTicketDao;
import traveldemo.hibernate.dao.CityDao;
import traveldemo.hibernate.entity.AirlineTicket;
import traveldemo.hibernate.entity.City;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by irwin on 11/18/14.
 */
@Path("airlineTicket")
public class AirlineTicketResource {

    private final AirlineTicketDao airlineTicketDao;
    private final CityDao cityDao;

    public AirlineTicketResource(AirlineTicketDao airlineTicketDao, CityDao cityDao) {
        this.airlineTicketDao = airlineTicketDao;
        this.cityDao = cityDao;
    }

    @GET
    @Path("/getAll")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<AirlineTicket> findAll() {
        return airlineTicketDao.getAll();
    }

    @GET
    @Path("/filter")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<AirlineTicket> filter(@QueryParam("destinationCity") int destinationCity,
                                      @QueryParam("fromCity") int fromCity,
                                      @QueryParam("landingDate") String landingDate,
                                      @QueryParam("arrivalDate") String arrivalDate) {

        City destination = cityDao.getById(destinationCity);
        City from = cityDao.getById(fromCity);

        DateTime landing = DateTime.parse(landingDate);
        DateTime arrival = DateTime.parse(arrivalDate);

        AirlineTicket filter = new AirlineTicket();
        filter.setDestinationCity(destination);
        filter.setFromCity(from);
        filter.setLandingDate(landing);
        filter.setArrivalDate(arrival);

        return airlineTicketDao.select(filter);
    }

}
