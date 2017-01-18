package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Path("/jira")
@Component
public class Jira {

    @POST
    public Response webhook(JSONObject request){
        System.out.println(request);
        return Response.ok().build();
    }
}
