package org.gluecoders.flock.integration;

import org.gluecoders.flock.bot.BotHandler;
import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.models.FlockUser;
import org.gluecoders.flock.models.JiraWebhookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Anand_Rajneesh on 1/19/2017.
 */
@Component
public class BotAdapter {

    @Autowired
    private Database db;
    @Autowired
    private BotHandler bot;

    private final static Logger logger = LoggerFactory.getLogger(BotAdapter.class);

    private FlockUser getFlockUser(String jiraUsername) {
        FlockUser flockUser = db.getFlockUserFromJiraUsername(jiraUsername);
        if (flockUser == null) {
            logger.info("Jira User not found for " + jiraUsername);
        }
        return flockUser;
    }

    public void issueAssigned(JiraWebhookEvent jiraEvent) {
        try {
            FlockUser flockUser = getFlockUser(jiraEvent.getUsername());
            if (flockUser != null) {
                bot.addAssignee(flockUser, jiraEvent.getTicketId(), jiraEvent.getSummary());
            }
        } catch (IOException e) {
           logger.error(e.getMessage());
        }
    }


    public void commentAdded(JiraWebhookEvent jiraEvent) {
        try {
            FlockUser flockUser = getFlockUser(jiraEvent.getUsername());
            if (flockUser != null) {
                bot.sendComment(flockUser,jiraEvent.getComment(),jiraEvent.getTicketId());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
