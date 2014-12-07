package travels.web.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import travels.shared.entity.City;
import travels.web.WebConfiguration;
import travels.web.util.RestClient;
import travels.web.util.VelocityUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by irwin on 12/2/14.
 */
@Path("/")
public class HomeResource {

    private final HttpClient httpClient;
    private final WebConfiguration config;
    private final ObjectMapper objectMapper;

    public HomeResource(HttpClient httpClient, WebConfiguration config,
                        ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response homePage() throws IOException {

        RestClient restClient = new RestClient(httpClient);
        String jsonAllCity = restClient.get(config.getModelUrl() + "/city", null, null);
        List<City> cityList = objectMapper.readValue(jsonAllCity, new TypeReference<List<City>>() {});

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("cityList", cityList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/home.vm", objectMap);

        return Response.ok(content).build();
    }

    @GET
    @Path("/showTickets")
    @Produces(MediaType.TEXT_HTML)
    public Response showTickets() throws IOException {

        RestClient restClient = new RestClient(httpClient);
        String jsonAllCity = restClient.get(config.getModelUrl() + "/city", null, null);
        List<City> cityList = objectMapper.readValue(jsonAllCity, new TypeReference<List<City>>() {});

        Map<String, Object> objectMap = new TreeMap();
        objectMap.put("cityList", cityList);

        VelocityUtil velocityUtil = new VelocityUtil(config);
        String content = velocityUtil.stringFromVm("/vita/home.vm", objectMap);

        return Response.ok(content).build();
    }

}
