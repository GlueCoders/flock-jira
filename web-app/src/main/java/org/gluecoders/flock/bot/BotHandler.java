package org.gluecoders.flock.bot;

import org.gluecoders.flock.models.FlockUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Satish_Bhuria on 1/19/2017.
 */
@Component
public class BotHandler {

    private final static Logger logger = LoggerFactory.getLogger(BotHandler.class);

    @Autowired
    HttpClientHandler httpClientHandler;

    public void sendComment(FlockUser flockUser, String comment , String ticketId) throws IOException {
        String message = String.format("Hi %s you have a comment on ticketId %s and comment as follows , %s"                ,
                flockUser.getFlockUsername() , ticketId , comment);
        logger.info("Message to sent - >" + message);
        httpClientHandler.test(flockUser.getFlockUserId() , message);
    }

    public void addAssignee(FlockUser flockUser , String ticketId , String summary) throws IOException {
        String message = String.format("Hi %s you just got a ticket id %s assigned to you and summary as follows, %s"                ,
                flockUser.getFlockUsername() , ticketId , summary);
        logger.info("Message to sent - >" + message);
        httpClientHandler.test(flockUser.getFlockUserId() , message);
    }
}
