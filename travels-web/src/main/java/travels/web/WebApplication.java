package travels.web;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import travels.shared.entity.*;
import travels.web.dao.AirlineTicketDao;
import travels.web.dao.CityDao;
import travels.web.dao.CustomerDao;
import travels.web.dao.CustomerTicketDao;
import travels.web.resource.CustomerResource;
import travels.web.resource.CustomerTicketResource;
import travels.web.resource.HomeResource;
import travels.web.resource.PassengerDataResource;
import travels.web.util.VelocityUtil;

import java.util.Properties;

/**
 * Created by irwin on 12/2/14.
 */
public class WebApplication extends Application<WebConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) throws Exception {
        new WebApplication().run(args);
    }

    private final HibernateBundle<WebConfiguration> hibernate = new HibernateBundle<WebConfiguration>
            (City.class,
                    CustomerTicket.class,
                    Airport.class,
                    Airline.class,
                    AirlineTicket.class,
                    Customer.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(WebConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<WebConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<WebConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(WebConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernate);

    }

    @Override
    public void run(WebConfiguration config, Environment environment) throws Exception {

        Properties velocityProperties = VelocityUtil.urlProperties(config.getVelocityCache(),
                config.getVelocityModificationCheck(), config.getAssetsUrl());

        Velocity.init(velocityProperties);
        LOGGER.info("init VELOCITY with param = {}", velocityProperties);

//        final HttpClient httpClient = new HttpClientBuilder(environment)
//                .using(config.getHttpClientConfiguration())
//                .build("travels-web");

        final CityDao cityDao = new CityDao(hibernate.getSessionFactory());
        final AirlineTicketDao airlineTicketDao = new AirlineTicketDao(hibernate.getSessionFactory());
        final CustomerTicketDao customerTicketDao = new CustomerTicketDao(hibernate.getSessionFactory());
        final CustomerDao customerDao = new CustomerDao(hibernate.getSessionFactory());

        HomeResource homeResource = new HomeResource(config, cityDao, airlineTicketDao);
        PassengerDataResource passengerDataResource = new PassengerDataResource(config, customerTicketDao,
                airlineTicketDao);
        CustomerTicketResource customerTicketResource = new CustomerTicketResource(config, customerTicketDao);
        CustomerResource customerResource = new CustomerResource(customerDao, config);

        environment.jersey().register(homeResource);
        environment.jersey().register(passengerDataResource);
        environment.jersey().register(customerTicketResource);
        environment.jersey().register(customerResource);

    }
}
