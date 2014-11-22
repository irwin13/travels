package travels.model.resource;

import travels.model.dao.CityDao;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by irwin on 11/22/14.
 */
@Path("/city")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CityResource {

    private final CityDao cityDao;

    public CityResource(CityDao cityDao) {
        this.cityDao = cityDao;
    }

}
