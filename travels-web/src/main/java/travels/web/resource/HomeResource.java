package travels.web.resource;

import org.apache.http.client.HttpClient;
import travels.web.WebConfiguration;
import travels.web.util.VelocityUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by irwin on 12/2/14.
 */
@Path("/")
public class HomeResource {

    private final HttpClient httpClient;
    private final WebConfiguration config;

    public HomeResource(HttpClient httpClient, WebConfiguration config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response homePage() {
        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/home.vm", null);
        return Response.ok(content).build();
    }
}
