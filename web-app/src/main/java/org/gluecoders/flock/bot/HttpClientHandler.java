package org.gluecoders.flock.bot;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);

    public static volatile String token = "b8f76fb0-48d9-416c-8834-08dbbb5662ec";

    public void test(String userId, String message) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(new AuthScope(BotConstants.PROXY, BotConstants.PROXY_PORT),
                    new UsernamePasswordCredentials(BotConstants.USERNAME, BotConstants.PASSWORD));
            HttpHost targetHost = new HttpHost(BotConstants.HOST, BotConstants.PORT, BotConstants.HTTPS);
            HttpHost proxy = new HttpHost(BotConstants.PROXY, BotConstants.PROXY_PORT);
            HttpPost request = new HttpPost(BotConstants.URI);
            Header header = new BasicHeader(BotConstants.COTENT_TYPE
                    ,BotConstants.APPLICATION_ENCODED);
            request.setHeader(header);
            httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair(BotConstants.TO, userId));
            urlParameters.add(new BasicNameValuePair(BotConstants.TEXT, message));
            urlParameters.add(new BasicNameValuePair(BotConstants.TOKEN, token));

            try {
                request.setEntity(new UrlEncodedFormEntity(urlParameters, HTTP.UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            HttpResponse response = httpclient.execute(targetHost, request);
            String responseString = new BasicResponseHandler().handleResponse(response);
            logger.info(responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
