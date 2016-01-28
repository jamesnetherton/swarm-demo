package com.jamesnetherton;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.swagger.SwaggerArchive;
import org.wildfly.swarm.undertow.WARArchive;

public class Main {
    public static void main(String... args) throws Exception {
        Container container = new Container();

        WARArchive deployment = ShrinkWrap.create(WARArchive.class, "swagger.war");
        deployment.staticContent();
        deployment.addClass(RestApplication.class);
        deployment.addClass(AudioService.class);

        SwaggerArchive archive = deployment.as(SwaggerArchive.class);
        archive.setResourcePackages("com.jamesnetherton");
        deployment.addAllDependencies();

        container.start().deploy(deployment);
    }
}
