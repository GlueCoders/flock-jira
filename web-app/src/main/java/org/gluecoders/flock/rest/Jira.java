package org.gluecoders.flock.rest;

import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.integration.BotAdapter;
import org.gluecoders.flock.jira.webhook.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    private final static Logger logger = LoggerFactory.getLogger(Jira.class);
    ExecutorService exe = Executors.newCachedThreadPool();

    @Autowired
    private BotAdapter bot;

    @POST
    public Response webhook(JSONObject request){
        logger.info("webhook event " +request.toString());
        exe.execute(new EventHandler(bot, request));
        return Response.ok().build();
    }
}
