package travels.web.resource;

import com.google.common.base.Strings;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import travels.shared.entity.AirlineTicket;
import travels.shared.entity.City;
import travels.web.WebConfiguration;
import travels.web.dao.AirlineTicketDao;
import travels.web.dao.CityDao;
import travels.web.util.VelocityUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by irwin on 12/2/14.
 */
@Path("/")
public class HomeResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeResource.class);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    private final WebConfiguration config;
    private final CityDao cityDao;
    private final AirlineTicketDao airlineTicketDao;

    public HomeResource(WebConfiguration config, CityDao cityDao,
                        AirlineTicketDao airlineTicketDao) {
        this.config = config;
        this.cityDao = cityDao;
        this.airlineTicketDao = airlineTicketDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public Response homePage() throws IOException {

        List<City> cityList = cityDao.getAll();

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("cityList", cityList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/home.vm", objectMap);

        return Response.ok(content).build();
    }

    @GET
    @Path("/showTickets")
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public Response showTickets(@QueryParam("destinationCity") String destinationCity,
                                @QueryParam("fromCity") String fromCity,
                                @QueryParam("leaveDate") String leaveDate,
                                @QueryParam("returnDate") String returnDate,
                                @QueryParam("maxFetch") @DefaultValue("15") int maxFetch) throws ParseException {

        AirlineTicket leavingFilter = new AirlineTicket();
        AirlineTicket returnFilter = new AirlineTicket();

        if (!Strings.isNullOrEmpty(destinationCity)) {
            City destination = cityDao.getById(destinationCity);
            if (destination != null) {
                leavingFilter.setDestinationCity(destination);
                returnFilter.setFromCity(destination);
            }
        }

        if (!Strings.isNullOrEmpty(fromCity)) {
            City from = cityDao.getById(fromCity);
            if (from != null) {
                leavingFilter.setFromCity(from);
                returnFilter.setDestinationCity(from);
            }
        }

        if (!Strings.isNullOrEmpty(leaveDate)) {
            Date leavingDate = SDF.parse(leaveDate);
            leavingFilter.setLandingDate(leavingDate);
        }

        boolean tiketPp = false;
        if (!Strings.isNullOrEmpty(returnDate)) {
            Date returningDate = SDF.parse(returnDate);
            returnFilter.setLandingDate(returningDate);
            tiketPp = true;
        }

        LOGGER.debug("leavingFilter = {}", leavingFilter);
        LOGGER.debug("returnFilter = {}", returnFilter);

        List<AirlineTicket> leaveAirlineTicketList = airlineTicketDao.select(leavingFilter, maxFetch);
        List<AirlineTicket> returnAirlineTicketList = new LinkedList<>();

        if (tiketPp) {
            returnAirlineTicketList = airlineTicketDao.select(returnFilter, maxFetch);
        }

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("leaveAirlineTicketList", leaveAirlineTicketList);
        objectMap.put("returnAirlineTicketList", returnAirlineTicketList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/showTickets.vm", objectMap);

        return Response.ok(content).build();
    }

}
