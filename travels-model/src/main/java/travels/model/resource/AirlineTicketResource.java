package travels.model.resource;

import com.google.common.base.Strings;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import travels.model.dao.AirlineTicketDao;
import travels.model.dao.CityDao;
import travels.shared.entity.AirlineTicket;
import travels.shared.entity.City;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by irwin on 12/7/14.
 */
@Path("/airlineTicket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AirlineTicketResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirlineTicketResource.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    private final AirlineTicketDao airlineTicketDao;
    private final CityDao cityDao;

    public AirlineTicketResource(AirlineTicketDao airlineTicketDao, CityDao cityDao) {
        this.airlineTicketDao = airlineTicketDao;
        this.cityDao = cityDao;
    }

    @Path("/select")
    @GET
    @UnitOfWork
    public List<AirlineTicket> filter(@QueryParam("destinationCity") String destinationCity,
                                      @QueryParam("fromCity") String fromCity,
                                      @QueryParam("landingDate") String landingDate,
                                      @QueryParam("arrivalDate") String arrivalDate,
                                      @QueryParam("maxFetch") @DefaultValue("15") int maxFetch) throws ParseException {

        AirlineTicket filter = new AirlineTicket();

        if (!Strings.isNullOrEmpty(destinationCity)) {
            City destination = cityDao.getByCode(destinationCity);
            filter.setDestinationCity(destination);
        }

        if (!Strings.isNullOrEmpty(fromCity)) {
            City from = cityDao.getByCode(fromCity);
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

        return airlineTicketDao.select(filter, maxFetch);
    }

}
