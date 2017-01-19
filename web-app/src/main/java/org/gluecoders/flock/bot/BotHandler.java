package org.gluecoders.flock.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Satish_Bhuria on 1/19/2017.
 */
@Component
public class BotHandler {

    @Autowired
    HttpClientHandler httpClientHandler;

    public void sendComment(String userId , String text) throws IOException {
         // send comment to bot for add comment
        HttpClientHandler httpClientHandler = new HttpClientHandler();
        httpClientHandler.sendComment(userId , text);
    }
}
