package traveldemo.resources;

import io.dropwizard.hibernate.UnitOfWork;
import traveldemo.hibernate.dao.Test1Dao;
import traveldemo.view.Test1View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by irwin on 11/16/14.
 */
@Path("/test1")
public class Test1Resource {

    private final Test1Dao dao;

    public Test1Resource(Test1Dao dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @UnitOfWork
    public Test1View findAll() {
        return new Test1View(dao.findAll());
    }
}
