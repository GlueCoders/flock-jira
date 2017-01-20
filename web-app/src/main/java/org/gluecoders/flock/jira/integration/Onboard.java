package org.gluecoders.flock.jira.integration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class Onboard {

    public final String API_KEY = "75aac847-fe8e-49d6-b44d-62e5abd24fc4";

    @Autowired
    private Database db;
    @Autowired
    private OAuthDancer oAuthDancer;
    @Autowired
    private RestDancer restDancer;

    public JiraCredentials onboardUserNewJira(String flockUsername, String jiraBaseUrl) throws Exception {
        try {
            JiraCredentials jiraCredentials = db.getJiraCredentials(jiraBaseUrl);
            if (jiraCredentials == null) {
                Keys newKeys = Keys.generate();
                jiraCredentials = new JiraCredentials(jiraBaseUrl, "", newKeys.getPrivateKey(), "flock-jira", newKeys.getPublicKey());
                db.saveJiraCredentials(jiraCredentials);
                return jiraCredentials;
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public String onboardUser(String flockUsername, String jiraBaseUrl) throws Exception {
        try {
            JiraCredentials jiraCredentials = db.getJiraCredentials(jiraBaseUrl);
            JiraUserCredentials jiraUserCredentials = new JiraUserCredentials();
            jiraUserCredentials.setJiraCredentials(jiraCredentials);
            oAuthDancer.getRedirectUrl(jiraCredentials, jiraUserCredentials);
            db.saveJiraUserCredentials(flockUsername, jiraUserCredentials);
            db.saveRequestToken(jiraUserCredentials.getRequestToken().getToken(), flockUsername);
            return jiraUserCredentials.getAuthorizationUrl();
        } catch (Exception e) {
            throw e;
        }
    }

    public void verifyUser(String flockUser, String verificationCode) throws Exception {
        try {
            JiraUserCredentials userCredentials = db.getJiraUserCredentials(flockUser);
            userCredentials.setOauthVerifier(verificationCode);
            oAuthDancer.getAccessToken(userCredentials.getJiraCredentials(), userCredentials);
            userCredentials.setJiraUsername(restDancer.getUsername(flockUser, userCredentials));
            db.saveJiraUserCredentials(flockUser, userCredentials);
        } catch (Exception e) {
            throw e;
        }
    }

    public void verify(String requestToken, String oauthVerifier) throws Exception {
        try {
            String flockUserId = db.getFlockUserFromRequestToken(requestToken);
            JiraUserCredentials userCredentials = db.getJiraUserCredentials(flockUserId);
            userCredentials.setOauthVerifier(oauthVerifier);
            oAuthDancer.getAccessToken(userCredentials.getJiraCredentials(), userCredentials);
            db.saveJiraUserCredentials(flockUserId, userCredentials);
        } catch (Exception e) {
            throw e;
        }
    }

    public String generateJWT(String token) throws Exception {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(API_KEY.getBytes("UTF-8")).parseClaimsJws(token);
            return claimsJws.getBody().get("userId").toString();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return null;
    }
}

