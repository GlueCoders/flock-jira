package org.gluecoders.flock.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/ping")
public class Ping {

    @GET
    public Response ping() {

        return Response.status(200).entity("Ping call").build();

    }

}