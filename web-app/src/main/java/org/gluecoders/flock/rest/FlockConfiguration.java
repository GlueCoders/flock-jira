package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.jira.integration.Onboard;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Path("/config")
@Component
public class FlockConfiguration {

    @Autowired
    private Onboard onboard;

    @POST
    @Path("/jiraurl")
    public Response addJiraConfiguration(JSONObject request) {
        try {
            JiraCredentials credentials = onboard.onboardUserNewJira(request.getString("userId"), request.getString("jiraUrl"));
            return Response.ok().entity(credentials).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @POST
    @Path("/token")
    public Response requestToken(JSONObject request) {
        try {
            String authUrl = onboard.onboardUser(request.getString("userId"), request.getString("jiraUrl"));
            return Response.ok().entity(new JSONObject(Collections.singletonMap("url", authUrl))).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/verify")
    public Response oauthVerify(@QueryParam("oauth_token") String requestToken, @QueryParam("oauth_verifier") String oauthVerifier) {
        try {
            onboard.verify(requestToken, oauthVerifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/eventtoken")
    public Response eventToken(@QueryParam("token") String token) {
        try {
            String jwtToken = onboard.generateJWT(token);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId" , jwtToken);
            return Response.ok().entity(jsonObject).type(MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }
}
