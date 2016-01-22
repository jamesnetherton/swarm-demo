package com.jamesnetherton;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
@Api( value = "/rest", description = "Manage names" )
public class MyJaxrsService {

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
        value = "Get a name",
        notes = "Returns the authors name",
        response = String.class
    )
    public String getName() {
        return "James";
    }
}
