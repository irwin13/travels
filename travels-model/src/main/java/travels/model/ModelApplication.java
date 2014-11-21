package travels.model;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by irwin on 11/22/14.
 */
public class ModelApplication extends Application<ModelConfiguration> {

    public static void main(String[] args) throws Exception {
        new ModelApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ModelConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<ModelConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ModelConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });

    }

    @Override
    public void run(ModelConfiguration modelConfiguration, Environment environment) throws Exception {

    }
}
