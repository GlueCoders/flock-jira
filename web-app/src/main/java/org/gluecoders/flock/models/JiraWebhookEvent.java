package org.gluecoders.flock.models;

/**
 * Created by Anand_Rajneesh on 1/19/2017.
 */
public class JiraWebhookEvent {

    private String username;
    private String ticketId;
    private String comment;
    private String summary;


    public String getUsername() {
        return username;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getComment() {
        return comment;
    }

    public String getSummary() {
        return summary;
    }

    public JiraWebhookEvent(String username, String ticketId, String comment, String summary) {
        this.username = username;
        this.ticketId = ticketId;
        this.comment = comment;
        this.summary = summary;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
