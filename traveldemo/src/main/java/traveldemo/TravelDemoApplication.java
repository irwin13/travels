package traveldemo;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import traveldemo.hibernate.dao.AirlineTicketDao;
import traveldemo.hibernate.dao.CityDao;
import traveldemo.hibernate.dao.Test1Dao;
import traveldemo.hibernate.entity.*;
import traveldemo.resources.HomePageResource;
import traveldemo.resources.Test1Resource;

/**
 * Created by irwin on 11/16/14.
 */
public class TravelDemoApplication extends Application<TravelDemoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TravelDemoApplication().run(args);
    }

    private final HibernateBundle<TravelDemoConfiguration> hibernate = new HibernateBundle<TravelDemoConfiguration>
            (Test1.class,
                    City.class,
                    Airline.class, AirlineTicket.class,
                    Airport.class, CustomerTicket.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(TravelDemoConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<TravelDemoConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<TravelDemoConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TravelDemoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle());

        // assets
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/png", "/png", null, "png"));
    }

    @Override
    public void run(TravelDemoConfiguration config,
                    Environment environment) throws ClassNotFoundException {
        final Test1Dao test1Dao = new Test1Dao(hibernate.getSessionFactory());
        final CityDao cityDao = new CityDao(hibernate.getSessionFactory());
        final AirlineTicketDao airlineTicketDao = new AirlineTicketDao(hibernate.getSessionFactory());

        environment.jersey().register(new Test1Resource(test1Dao));
        environment.jersey().register(new HomePageResource(cityDao, airlineTicketDao));
    }
}
