package traveldemo.resources;

import com.google.common.base.Strings;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import traveldemo.hibernate.dao.AirlineTicketDao;
import traveldemo.hibernate.dao.CityDao;
import traveldemo.hibernate.entity.AirlineTicket;
import traveldemo.hibernate.entity.City;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by irwin on 11/18/14.
 */
@Path("airlineTicket")
public class AirlineTicketResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineTicketResource.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

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
                                      @QueryParam("arrivalDate") String arrivalDate) throws ParseException {

        AirlineTicket filter = new AirlineTicket();

        if (destinationCity > 0) {
            City destination = cityDao.getById(destinationCity);
            filter.setDestinationCity(destination);
        }

        if (fromCity > 0) {
            City from = cityDao.getById(fromCity);
            filter.setFromCity(from);
        }

        if (!Strings.isNullOrEmpty(landingDate)) {
            Date landing = SDF.parse(landingDate);
            filter.setLandingDate(landing);
        }

        if (!Strings.isNullOrEmpty(arrivalDate)) {
            Date arrival = SDF.parse(arrivalDate);
            filter.setArrivalDate(arrival);
        }

        LOGGER.debug("filter = {}", filter);

        return airlineTicketDao.select(filter);
    }

}
