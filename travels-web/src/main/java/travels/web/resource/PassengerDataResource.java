package travels.web.resource;

import io.dropwizard.hibernate.UnitOfWork;
import travels.shared.entity.AirlineTicket;
import travels.shared.entity.CustomerTicket;
import travels.web.WebConfiguration;
import travels.web.dao.AirlineTicketDao;
import travels.web.dao.CustomerTicketDao;
import travels.web.util.VelocityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by irwin on 12/10/14.
 */
@Path("/passengerData")
public class PassengerDataResource {

    private final WebConfiguration config;
    private final CustomerTicketDao customerTicketDao;
    private final AirlineTicketDao airlineTicketDao;

    public PassengerDataResource(WebConfiguration config, CustomerTicketDao customerTicketDao,
                                 AirlineTicketDao airlineTicketDao) {
        this.config = config;
        this.customerTicketDao = customerTicketDao;
        this.airlineTicketDao = airlineTicketDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public Response page(@Context HttpServletRequest request) {
        String[] airlineTicketArray = request.getParameterValues("selectedTicket");
        List<AirlineTicket> airlineTicketList = new LinkedList<>();
        for (String ticketId : airlineTicketArray) {
            AirlineTicket airlineTicket = airlineTicketDao.getById(ticketId);
            if (airlineTicket != null) {
                airlineTicketList.add(airlineTicket);
            }
        }

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("airlineTicketList", airlineTicketList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/passengerData.vm", objectMap);

        return Response.ok(content).build();
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public Response submitForm(MultivaluedMap<String, String> multivaluedMap) {

        List<String> urlList = new LinkedList<>();
        String customerTicketUrl = config.getAppContextUrl() + "/customerTicket/";

        List<String> selectedTicket = multivaluedMap.get("selectedTicket");
        for (String ticketId : selectedTicket) {
            CustomerTicket customerTicket = new CustomerTicket();
            customerTicket.setCustomerName(multivaluedMap.getFirst("namaLengkap"));
            customerTicket.setCustomerPhone(multivaluedMap.getFirst("telepon"));
            customerTicket.setCustomerEmail(multivaluedMap.getFirst("email"));
            customerTicket.setPaymentMethod(multivaluedMap.getFirst("paymentMethod"));
            customerTicket.setPaymentStatus("booked");
            customerTicket.setActive("Y");
            customerTicket.setCreateDate(new Date());
            customerTicket.setLastUpdateDate(new Date());
            customerTicket.setCreateBy("SYSTEM");
            customerTicket.setLastUpdateBy("SYSTEM");

            AirlineTicket airlineTicket = airlineTicketDao.getById(ticketId);
            customerTicket.setAirlineTicket(airlineTicket);

            CustomerTicket inserted = customerTicketDao.insert(customerTicket);
            urlList.add(customerTicketUrl + inserted.getId());
        }

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("urlList", urlList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/confirmedTx.vm", objectMap);

        return Response.ok(content).build();
    }

}
