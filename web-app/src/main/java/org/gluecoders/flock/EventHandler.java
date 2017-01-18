package org.gluecoders.flock;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.jira.integration.RestDancer;
import org.gluecoders.flock.models.FlockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class EventHandler {

    @Autowired
    private Database db;
    @Autowired
    private RestDancer restDancer;

    public void saveNewUser(JSONObject json) throws JSONException {
        FlockUser flockUser = new FlockUser(json.getString("token"), json.getString("userId"), json.getString("userId"));
        db.saveNewFlockUser(flockUser);
    }

    public void addComment(JSONObject json) throws Exception{
        String userId = json.getString("userId");
        String text = json.getString("text");
        String[] split = text.split(" ");
        String ticketId = split[1];
        String comment = split[2];
        restDancer.addComment(userId, db.getJiraUserCredentials(userId), comment, ticketId);
    }

}
