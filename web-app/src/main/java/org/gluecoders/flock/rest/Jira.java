package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.integration.BotAdapter;
import org.gluecoders.flock.jira.webhook.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Path("/jira")
@Component
public class Jira {

    ExecutorService exe = Executors.newCachedThreadPool();

    @Autowired
    private BotAdapter bot;

    @POST
    public Response webhook(JSONObject request){
        System.out.println(request);
        exe.execute(new EventHandler(bot, request));
        return Response.ok().build();
    }
}
