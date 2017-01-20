package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
@Path("/events")
public class Flock {

    private final static Logger logger = LoggerFactory.getLogger(Flock.class);

    @Autowired
    private EventHandler eventHandler;

    @POST
    public Response listen(@HeaderParam("X-Flock-Event-Token") String token, JSONObject request){
        logger.info("Event listener "+request.toString());
        try {
            String event = request.getString("name");
            if(event.equalsIgnoreCase("app.install")){
                eventHandler.saveNewUser(request);
            }

            if(event.equalsIgnoreCase("client.slashCommand")){
                String command = request.getString("command");
                if(command.equalsIgnoreCase("jira")){
                    if(request.getString("text").contains("comment")){
                        eventHandler.addComment(request);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return Response.ok().build();
    }
}
