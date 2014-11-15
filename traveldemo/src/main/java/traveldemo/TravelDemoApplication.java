package traveldemo;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import traveldemo.hibernate.dao.Test1Dao;
import traveldemo.hibernate.entity.Test1;
import traveldemo.resources.Test1Resource;

/**
 * Created by irwin on 11/16/14.
 */
public class TravelDemoApplication extends Application<TravelDemoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TravelDemoApplication().run(args);
    }

    private final HibernateBundle<TravelDemoConfiguration> hibernate = new HibernateBundle<TravelDemoConfiguration>
            (Test1.class) {

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
    }

    @Override
    public void run(TravelDemoConfiguration config,
                    Environment environment) throws ClassNotFoundException {
        final Test1Dao dao = new Test1Dao(hibernate.getSessionFactory());
        environment.jersey().register(new Test1Resource(dao));
    }
}
