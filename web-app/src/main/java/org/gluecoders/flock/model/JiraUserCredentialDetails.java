package org.gluecoders.flock.model;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;

import javax.persistence.*;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Entity
@Table(name = "JIRA_USERCREDENTIALS")
public class JiraUserCredentialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "FLOCKUSERNAME")
    private String flockUsername;
    @Column(name = "REQUEST_TOKEN")
    private String requestToken;
    @Column(name = "REQUEST_TOKENSECRET")
    private String requsttokenSecret;
    @Column(name = "ACCESS_TOKEN", length=4096)
    private OAuth1AccessToken accessToken;
    @Column(name = "AUTH_VERIFIER")
    private String oauthVerifier;
    @Column(name = "JIRAUSERNAME")
    private String jiraUsername;
    @ManyToOne
    @JoinColumn(name = "JIRA_CREDENTIALS_FK")
    private JiraCredentialDetails jiraCredentials;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OAuth1AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(OAuth1AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getRequsttokenSecret() {
        return requsttokenSecret;
    }

    public void setRequsttokenSecret(String requsttokenSecret) {
        this.requsttokenSecret = requsttokenSecret;
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


    public JiraCredentialDetails getJiraCredentials() {
        return jiraCredentials;
    }

    public void setJiraCredentials(JiraCredentialDetails jiraCredentials) {
        this.jiraCredentials = jiraCredentials;
    }

    public String getFlockUsername() {
        return flockUsername;
    }

    public void setFlockUsername(String flockUsername) {
        this.flockUsername = flockUsername;
    }
}
