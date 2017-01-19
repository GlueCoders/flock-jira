package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.bot.HttpClientHandler;
import org.gluecoders.flock.models.JiraCredentials;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Satish_Bhuria on 1/19/2017.
 */
@Component
@Path("/change")
public class BotToken {

    @POST
    @Path("/bot/{token}")
    public Response changeToken(@PathParam("token") String token) {
        try {
            HttpClientHandler.token = token;
            return Response.ok().entity("token changed").type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
