package travels.web.resource;

import io.dropwizard.hibernate.UnitOfWork;
import travels.shared.entity.AirlineTicket;
import travels.shared.entity.CustomerTicket;
import travels.web.WebConfiguration;
import travels.web.dao.CustomerTicketDao;
import travels.web.util.VelocityUtil;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by irwin on 12/10/14.
 */
@Path("/customerTicket")
public class CustomerTicketResource {

    private final WebConfiguration config;
    private final CustomerTicketDao customerTicketDao;

    public CustomerTicketResource(WebConfiguration config, CustomerTicketDao customerTicketDao) {
        this.config = config;
        this.customerTicketDao = customerTicketDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{id}")
    @UnitOfWork
    public Response page(@PathParam("id") String id) {
        CustomerTicket customerTicket = customerTicketDao.getById(id);
        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("customerTicket", customerTicket);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/customerTicketStatus.vm", objectMap);

        return Response.ok(content).build();
    }

}
