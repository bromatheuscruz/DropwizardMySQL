package iocruz.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import iocruz.dropwizard.db.ProductDAO;
import iocruz.dropwizard.resources.ProductResource;
import org.jdbi.v3.core.Jdbi;

public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception
    {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() { return "Dropwizard"; }

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap)
    {
        bootstrap.addBundle(new MigrationsBundle<DropwizardConfiguration>() {

            @Override
            public PooledDataSourceFactory getDataSourceFactory(DropwizardConfiguration dropwizardConfiguration) {
                return dropwizardConfiguration.getDatabase();
            }
        });
    }

    @Override
    public void run(final DropwizardConfiguration configuration, final Environment environment)
    {
        // jdbi
        var jdbiFactory = new JdbiFactory();
        var jdbi = jdbiFactory.build(environment, configuration.getDatabase(), "mysql");
        var productDAO = jdbi.onDemand(ProductDAO.class);

        // jersey
        var productResource = new ProductResource(productDAO);
        environment.jersey().register(productResource);
    }

}
