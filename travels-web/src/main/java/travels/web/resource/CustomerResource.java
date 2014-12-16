package travels.web.resource;

import travels.web.WebConfiguration;
import travels.web.dao.CustomerDao;
import travels.web.util.VelocityUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by irwin on 12/16/14.
 */
@Path("/customer")
public class CustomerResource {

    private final CustomerDao customerDao;
    private final WebConfiguration config;

    public CustomerResource(CustomerDao customerDao, WebConfiguration config) {
        this.customerDao = customerDao;
        this.config = config;
    }

    @GET
    @Path("/registration")
    @Produces(MediaType.TEXT_HTML)
    public Response registrationPage() {
        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/customerRegistration.vm", null);

        return Response.ok(content).build();

    }
}
