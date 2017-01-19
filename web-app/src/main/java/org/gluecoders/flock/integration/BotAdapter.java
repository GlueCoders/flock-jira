package org.gluecoders.flock.integration;

import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.models.FlockUser;
import org.gluecoders.flock.models.JiraWebhookEvent;
import org.gluecoders.flock.rest.Flock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/19/2017.
 */
@Component
public class BotAdapter {

    @Autowired
    private Database db;

    private final static Logger logger = LoggerFactory.getLogger(BotAdapter.class);

    private FlockUser getFlockUser(String jiraUsername){
        FlockUser flockUser = db.getFlockUserFromJiraUsername(jiraUsername);
        if(flockUser == null){
            logger.info("Jira User not found for "+jiraUsername);
        }
        return flockUser;
    }

    public void issueAssigned(JiraWebhookEvent jiraEvent){
        FlockUser flockUser = getFlockUser(jiraEvent.getUsername());
        if(flockUser!=null){

        }
    }


    public void commentAdded(JiraWebhookEvent jiraEvent){
        FlockUser flockUser = getFlockUser(jiraEvent.getUsername());
        if(flockUser!=null){

        }
    }

}
