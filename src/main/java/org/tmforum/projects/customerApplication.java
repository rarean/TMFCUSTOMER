package org.tmforum.projects;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.tmforum.projects.health.TemplateHealthCheck;

public class customerApplication extends Application<customerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new customerApplication().run(args);
    }

    @Override
    public String getName() {
        return "customer";
    }

    @Override
    public void initialize(final Bootstrap<customerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final customerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }

}
