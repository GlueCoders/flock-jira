package org.gluecoders.flock.bot;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satish_Bhuria on 1/19/2017.
 */
@Component
public class HttpClientHandler {

    public static volatile String token = "b8f76fb0-48d9-416c-8834-08dbbb5662ec";

    String url = "https://api.flock.co/v1/chat.sendMessage";

    public void sendComment(String userId, String message) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("to", userId));
        urlParameters.add(new BasicNameValuePair("text", message));
        urlParameters.add(new BasicNameValuePair("token", token));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
    }

    public void issueAssigned(String userId , String message) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("to", userId));
        urlParameters.add(new BasicNameValuePair("text", message));
        urlParameters.add(new BasicNameValuePair("token", token));

        try {
            post.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
    }
}
