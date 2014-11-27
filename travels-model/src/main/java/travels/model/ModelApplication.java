package travels.model;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import travels.model.dao.CityDao;
import travels.model.resource.CityResource;
import travels.shared.entity.City;

/**
 * Created by irwin on 11/22/14.
 */
public class ModelApplication extends Application<ModelConfiguration> {

    public static void main(String[] args) throws Exception {
        new ModelApplication().run(args);
    }

    private final HibernateBundle<ModelConfiguration> hibernate = new HibernateBundle<ModelConfiguration>
            (City.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(ModelConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<ModelConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<ModelConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ModelConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ModelConfiguration modelConfiguration, Environment environment) throws Exception {
        final CityDao cityDao = new CityDao(hibernate.getSessionFactory());
        final CityResource cityResource = new CityResource(cityDao);
        environment.jersey().register(cityResource);

    }
}
