package org.gluecoders.flock.bot;

import java.io.IOException;

/**
 * Created by Satish_Bhuria on 1/19/2017.
 */
public class Test {

    public static void main(String[] args) {
        HttpClientHandler httpClientHandler = new HttpClientHandler();
        try {
            httpClientHandler.sendComment("asasss" , "hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
