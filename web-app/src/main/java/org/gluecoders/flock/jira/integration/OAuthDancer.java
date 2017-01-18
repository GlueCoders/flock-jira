package org.gluecoders.flock.jira.integration;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class OAuthDancer {


    private OAuth10aService getScribe(JiraCredentials jiraCredentials) {
        return new ServiceBuilder()
                .apiKey(jiraCredentials.getConsumerKey())
                .apiSecret(jiraCredentials.getPrivateKey())
                .build(new OAuthAPI(jiraCredentials.getBaseUrl(), jiraCredentials.getPrivateKey()));
    }


    public JiraUserCredentials getRedirectUrl(JiraCredentials jiraCredentials, JiraUserCredentials userCredentials) throws Exception {
        try {
            OAuth10aService service = getScribe(jiraCredentials);
            OAuth1RequestToken requestToken = service.getRequestToken();
            String authUrl = service.getAuthorizationUrl(requestToken);
            userCredentials.setAuthorizationUrl(authUrl);
            userCredentials.setRequestToken(requestToken);
            return userCredentials;
        } catch (Exception e) {
            throw e;
        }
    }

    public JiraUserCredentials getAccessToken(JiraCredentials jiraCredentials, JiraUserCredentials userCredentials) throws Exception {
        try{
            OAuth10aService service = getScribe(jiraCredentials);
            OAuth1AccessToken accessToken = service.getAccessToken(userCredentials.getRequestToken(), userCredentials.getOauthVerifier());
            userCredentials.setAccessToken(accessToken);
            return userCredentials;
        }catch(Exception e){
            throw e;
        }
    }

    public Response signRequestAndExecute(OAuthRequest request, JiraCredentials jiraCredentials, JiraUserCredentials userCredentials) throws Exception {
        try{
            OAuth10aService service = getScribe(jiraCredentials);
            service.signRequest(userCredentials.getAccessToken(), request);
            return service.execute(request);
        }catch(Exception e){
            throw e;
        }
    }


}
