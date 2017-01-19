package org.gluecoders.flock.jira.webhook;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.gluecoders.flock.integration.BotAdapter;
import org.gluecoders.flock.models.JiraWebhookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Anand_Rajneesh on 1/19/2017.
 */
public class EventHandler implements  Runnable{

    private final static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private enum Event { ISSUE_CREATED, COMMENT_CREATED, ISSUE_ASSIGNED }

    private final JSONObject jiraEvent;
    private final BotAdapter bot;

    public EventHandler(BotAdapter bot, JSONObject jiraEvent) {
        this.jiraEvent = jiraEvent;
        this.bot = bot;
    }

    private String getTicketId() throws JSONException {
        return jiraEvent.getJSONObject("issue").getString("key");
    }

    public void run() {
        try {
            logger.info("Webhook event");
            String webHookEvent = jiraEvent.getString("webhookEvent");
            if(webHookEvent.equalsIgnoreCase("jira:issue_created")){
                logger.info("Issue Created");
                handleEvent(Event.ISSUE_CREATED);
            }else if(webHookEvent.equalsIgnoreCase("jira:issue_updated")){
                logger.info("Issue updated");
                String issueEventTypeName = jiraEvent.getString("issue_event_type_name");
                if(issueEventTypeName.equalsIgnoreCase("issue_assigned")){
                    handleEvent(Event.ISSUE_ASSIGNED);
                }else if(issueEventTypeName.equalsIgnoreCase("issue_commented")){
                    handleEvent(Event.COMMENT_CREATED);
                }
                else{
                    logger.info("Event discarded: "+webHookEvent+" "+issueEventTypeName);
                }
            }else{
                logger.info("Event discarded: "+webHookEvent);
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    private void handleEvent(Event eventType) throws JSONException {
        JSONObject issue = jiraEvent.getJSONObject("issue");
        String ticketId = issue.getString("key");
        String summary = issue.getString("summary");
        String assignee = issue.getJSONObject("assignee").getString("name");
        JiraWebhookEvent jiraWebhookEvent = new JiraWebhookEvent(assignee, ticketId, "", summary);
        logger.info(String.format("Event type %s for user %s, ticketid %s", eventType, assignee, ticketId));
        switch (eventType){
            case ISSUE_CREATED:
            case ISSUE_ASSIGNED:
                bot.issueAssigned(jiraWebhookEvent);
                break;
            case COMMENT_CREATED:
                String comment = jiraEvent.getJSONObject("comment").getString("body");
                jiraWebhookEvent.setComment(comment);
                bot.commentAdded(jiraWebhookEvent);
                break;
            default:
                logger.error("No event type mapped for "+eventType);
        }
    }
}
