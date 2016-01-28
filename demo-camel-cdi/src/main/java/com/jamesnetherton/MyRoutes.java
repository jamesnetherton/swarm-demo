package com.jamesnetherton;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;

@ContextName
public class MyRoutes extends RouteBuilder {

    @Inject
    @Uri("file:/home/james/Documents/art?delay=3000&noop=true&idempotent=false")
    private Endpoint inputEndpoint;

    @Override
    public void configure() throws Exception {
        from(inputEndpoint).to("bean:rainbowBean");
    }

}
