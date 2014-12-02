package travels.web;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import travels.web.resource.HomeResource;
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

    @Override
    public void initialize(Bootstrap<WebConfiguration> bootstrap) {

    }

    @Override
    public void run(WebConfiguration config, Environment environment) throws Exception {

        Properties velocityProperties = VelocityUtil.urlProperties(config.getVelocityCache(),
                config.getVelocityModificationCheck(), config.getAssetsUrl());

        Velocity.init(velocityProperties);
        LOGGER.info("init VELOCITY with param = {}", velocityProperties);

        final HttpClient httpClient = new HttpClientBuilder(environment)
                .using(config.getHttpClientConfiguration())
                .build("travels-web");

        HomeResource homeResource = new HomeResource(httpClient, config);
        environment.jersey().register(homeResource);
    }
}
