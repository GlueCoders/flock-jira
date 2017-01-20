package org.gluecoders.flock;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.jira.integration.RestDancer;
import org.gluecoders.flock.models.FlockUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class EventHandler {

    private final static Logger logger = LoggerFactory.getLogger(EventHandler.class);
    @Autowired
    private Database db;
    @Autowired
    private RestDancer restDancer;

    public void saveNewUser(JSONObject json) throws JSONException {
        logger.info("Save new user "+json.toString());
        FlockUser flockUser = new FlockUser(json.getString("token"), json.getString("userId"), json.getString("userId"));
        db.saveNewFlockUser(flockUser);
        logger.info("User saved");
    }

    public void addComment(JSONObject json) throws Exception{
        logger.info("Add comment event" + json.toString());
        String userId = json.getString("userId");
        String text = json.getString("text");
        String[] split = text.split(" ");
        String ticketId = split[1];
        String comment = text.substring(9+ticketId.length());
        restDancer.addComment(userId, db.getJiraUserCredentials(userId), comment, ticketId);
        logger.info("Comment added");
    }

}
