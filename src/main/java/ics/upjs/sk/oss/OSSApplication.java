package ics.upjs.sk.oss;

import ics.upjs.sk.core.scs.DefiniciaSCS;
import ics.upjs.sk.core.scs.SlovoSCS;
import ics.upjs.sk.core.ssj.DefiniciaSSJ;
import ics.upjs.sk.core.ssj.MorfologickaDefiniciaSSJ;
import ics.upjs.sk.core.ssj.SlovoSSJ;
import ics.upjs.sk.core.synonyma.Synonymum;
import ics.upjs.sk.dao.ScsDao;
import ics.upjs.sk.dao.SsjDao;
import ics.upjs.sk.dao.SynonymumDao;
import ics.upjs.sk.resource.OssResource;
import ics.upjs.sk.resource.SSJResource;
import ics.upjs.sk.resource.SynonymumResource;
import ics.upjs.sk.service.OssService;
import ics.upjs.sk.service.SSJService;
import ics.upjs.sk.service.SynonymumService;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author Raven
 */
public class OSSApplication extends Application<OSSConfiguration> {
    
    /********************************************************************************************************************
     ********************************************************************************************************************
     ***************************************** PRIDAT KAZDU NOVU ENTITU *****************************************
     ******************************** ABY HIBERNATE VEDEL NAMAPOVAT ENTITU ********************************
     ********************************************************************************************************************
     *******************************************************************************************************************/
    
    private final HibernateBundle<OSSConfiguration> hibernateBundle = new HibernateBundle<OSSConfiguration>(
            Synonymum.class,
            SlovoSCS.class,
            DefiniciaSCS.class,
            SlovoSSJ.class,
            DefiniciaSSJ.class,
            MorfologickaDefiniciaSSJ.class
    ) {
        @Override
        public DataSourceFactory getDataSourceFactory(OSSConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    
    public static void main(final String[] args) throws Exception {
        new OSSApplication().run(args);
    }
    
    @Override
    public String getName() {
        return "OSS";
    }
    
    @Override
    public void initialize(final Bootstrap<OSSConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
    
    @Override
    public void run(final OSSConfiguration configuration, final Environment environment) throws Exception {
        
        /**
         * Create DAO's
         */
        final  ScsDao scsDao = new ScsDao(hibernateBundle.getSessionFactory());
        final SsjDao ssjDao = new SsjDao(hibernateBundle.getSessionFactory());
        final SynonymumDao synonymumDao = new SynonymumDao(hibernateBundle.getSessionFactory());
        
        /**
         * Create Setvice's (for now just one)
         */
        final SynonymumService synonymumService= new SynonymumService(synonymumDao);
        final SSJService ssjService = new SSJService(ssjDao);
        final  OssService ossService = new OssService(scsDao, ssjDao, synonymumDao);
        
        /**
         * Create Jesrsey client
         * Register Resource's (for now just one)
         */
        final Client client = new JerseyClientBuilder(environment)
                .using(configuration.getJerseyClientConfiguration())
                .build(getName());
        environment.jersey().register(new OssResource(ossService));
        environment.jersey().register(new SynonymumResource(synonymumService));
        environment.jersey().register(new SSJResource(ssjService));
        
        /**
         * Create CORS headers
         * Configure CORS param's
         * Add URL mapping
         */
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin, Authorization");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        
    }
    
}
