package ics.upjs.sk.oss;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Raven
 */
public class OSSConfiguration extends Configuration implements AssetsBundleConfiguration {
    
    @Valid
    @NotNull
    @JsonProperty
    private final AssetsConfiguration assets = new AssetsConfiguration();
    
    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }
    
    @NotNull
    @Valid
    private final DataSourceFactory dataSourceFactory = new DataSourceFactory();
    
    @Valid
    @NotNull
    private final JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();
    
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
    
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }
    
}
