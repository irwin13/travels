package travels.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by irwin on 12/2/14.
 */
public class WebConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient = new HttpClientConfiguration();

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    private String modelUrl;

    @Valid
    @NotNull
    private String assetsUrl;

    @Valid
    @NotNull
    private String appContextUrl;

    @Valid
    @NotNull
    private String velocityCache;

    @Valid
    @NotNull
    private String velocityModificationCheck;

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public String getAssetsUrl() {
        return assetsUrl;
    }

    public String getVelocityCache() {
        return velocityCache;
    }

    public String getVelocityModificationCheck() {
        return velocityModificationCheck;
    }

    public String getAppContextUrl() {
        return appContextUrl;
    }
}
