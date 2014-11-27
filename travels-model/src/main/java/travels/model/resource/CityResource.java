package travels.model.resource;

import com.google.common.base.Strings;
import io.dropwizard.hibernate.UnitOfWork;
import travels.model.dao.CityDao;
import travels.shared.entity.City;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

    @Path("/{code}")
    @GET
    @UnitOfWork
    public City getById(@PathParam("code") String code) {
        return cityDao.getByCode(code);
    }

    @GET
    @UnitOfWork
    public List<City> get(@QueryParam("name") String name) {
        if (!Strings.isNullOrEmpty(name)) {
            return cityDao.getByName(name);
        } else {
            return cityDao.getAll();
        }
    }
}
