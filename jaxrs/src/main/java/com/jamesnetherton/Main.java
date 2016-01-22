package com.jamesnetherton;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.swagger.SwaggerArchive;

public class Main {
    public static void main(String... args) throws Exception {
        Container container = new Container();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class, "swagger-app.war");
        deployment.addClass(MyJaxrsService.class);

        SwaggerArchive archive = deployment.as(SwaggerArchive.class);
        archive.setResourcePackages("com.jamesnetherton");
        deployment.addAllDependencies();

        container.start().deploy(deployment);
    }
}
