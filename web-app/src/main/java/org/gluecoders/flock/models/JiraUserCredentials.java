package org.gluecoders.flock.models;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class JiraUserCredentials {

    private OAuth1RequestToken requestToken;
    private String authorizationUrl;
    private OAuth1AccessToken accessToken;
    private String oauthVerifier;
    private String jiraUsername;
    private JiraCredentials jiraCredentials;

    public OAuth1AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth1AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }

    public OAuth1RequestToken getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(OAuth1RequestToken requestToken) {
        this.requestToken = requestToken;
    }

    public String getOauthVerifier() {
        return oauthVerifier;
    }

    public void setOauthVerifier(String oauthVerifier) {
        this.oauthVerifier = oauthVerifier;
    }

    public String getJiraUsername() {
        return jiraUsername;
    }

    public void setJiraUsername(String jiraUsername) {
        this.jiraUsername = jiraUsername;
    }

    public JiraCredentials getJiraCredentials() {
        return jiraCredentials;
    }

    public void setJiraCredentials(JiraCredentials jiraCredentials) {
        this.jiraCredentials = jiraCredentials;
    }


    @Override
    public String toString() {
        return "JiraUserCredentials{" +
                "requestToken=" + requestToken +
                ", authorizationUrl='" + authorizationUrl + '\'' +
                ", accessToken=" + accessToken +
                ", oauthVerifier='" + oauthVerifier + '\'' +
                ", jiraUsername='" + jiraUsername + '\'' +
                ", jiraCredentials=" + jiraCredentials +
                '}';
    }
}
