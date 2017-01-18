package org.gluecoders.flock.jira.integration;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class RestDancer {

    @Autowired
    private RestAPI api;
    @Autowired
    private OAuthDancer oAuthDancer;
    @Autowired
    private Database db;

    //add comment
    //get myself
    //assign issue

    public String getUsername(String flockUser, JiraUserCredentials userCredentials) throws Exception {
        //JiraUserCredentials userCredentials = db.getJiraUserCredentials(flockUser);
        OAuthRequest request = api.getSelf(userCredentials.getJiraCredentials().getBaseUrl());
        Response response = oAuthDancer.signRequestAndExecute(request, userCredentials.getJiraCredentials(), userCredentials);
        JSONObject json = new JSONObject(response.getBody());
        return json.getString("name");
    }

    public void addComment(String flockUser, JiraUserCredentials userCredentials, String comment, String ticketId) throws Exception{
        OAuthRequest request = api.addCommentRequest(userCredentials.getJiraCredentials().getBaseUrl(),ticketId,comment);
        Response response = oAuthDancer.signRequestAndExecute(request,userCredentials.getJiraCredentials(),userCredentials);
    }

}
