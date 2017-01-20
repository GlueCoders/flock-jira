package org.gluecoders.flock.dao;

import com.github.scribejava.core.model.OAuth1RequestToken;
import org.gluecoders.flock.model.FlockUsers;
import org.gluecoders.flock.model.JiraCredentialDetails;
import org.gluecoders.flock.model.JiraUserCredentialDetails;
import org.gluecoders.flock.models.FlockUser;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public final class Database {

    private final static Logger logger = LoggerFactory.getLogger(Database.class);
    private final Map<String, JiraCredentials> jiraMappings = new ConcurrentHashMap<String, JiraCredentials>();
    private final Map<String, JiraUserCredentials> jiraUsersMappings = new ConcurrentHashMap<String, JiraUserCredentials>();
    private final Map<String, FlockUser> flockUserMappings = new ConcurrentHashMap<String, FlockUser>();
    private final Map<String, String> requestTokenMappings = new ConcurrentHashMap<String, String>();

    @Autowired
    private FlockImpl db;

    public JiraUserCredentials getJiraUserCredentials(String flockUser) {
        logger.info("getJiraUserCredentials "+flockUser);
        JiraUserCredentialDetails o =  db.getJiraUserCredentials(flockUser);
        JiraUserCredentials x = new JiraUserCredentials();
        x.setJiraUsername(o.getJiraUsername());
        x.setId(o.getId());
        x.setAccessToken(o.getAccessToken());
        x.setOauthVerifier(o.getOauthVerifier());
        x.setRequestToken(new OAuth1RequestToken(o.getRequestToken(), o.getRequsttokenSecret()));
        JiraCredentialDetails t = o.getJiraCredentials();
        x.setJiraCredentials(new JiraCredentials(t.getBaseUrl(), "", t.getPrivateKey(), t.getConsumerKey(), t.getPublicKey()));
        logger.info("exit getJiraUserCredentials");
        return x;
        //return jiraUsersMappings.get(flockUser);
    }

    public JiraCredentials getJiraCredentials(String jiraUrl) {
        logger.info("getJiraCredentials "+jiraUrl);
        JiraCredentialDetails o = db.getJiraCredentials(jiraUrl);
        if(o == null) {return null;}
        JiraCredentials x = new JiraCredentials(jiraUrl, "", o.getPrivateKey(), o.getConsumerKey(), o.getPublicKey());
        x.setId(o.getId());
        logger.info("exit getJiraCredentials ");
        return x;
        //return jiraMappings.get(jiraUrl);
    }

    public void saveJiraCredentials(JiraCredentials credentials) {
        logger.info("saveJiraCredentials "+credentials.getBaseUrl());
        JiraCredentialDetails o = new JiraCredentialDetails();
        o.setBaseUrl(credentials.getBaseUrl());
        o.setConsumerKey(credentials.getConsumerKey());
        o.setPrivateKey(credentials.getPrivateKey());
        o.setPublicKey(credentials.getPublicKey());
        o.setWebhookId(credentials.getWebhookId());
        if(credentials.getId() != 0) {
            o.setId(credentials.getId());
        }
        db.saveJiraCredentials(o);
        credentials.setId(o.getId());
        logger.info("saveJiraCredentials");
        //jiraMappings.put(credentials.getBaseUrl(), credentials);
    }

    public void saveJiraUserCredentials(String flockUser, JiraUserCredentials userCredentials) {
        logger.info("saveJiraUserCredentials "+flockUser);
        JiraCredentials credentials = userCredentials.getJiraCredentials();
        JiraCredentialDetails o = new JiraCredentialDetails();
        o.setBaseUrl(credentials.getBaseUrl());
        o.setConsumerKey(credentials.getConsumerKey());
        o.setPrivateKey(credentials.getPrivateKey());
        o.setPublicKey(credentials.getPublicKey());
        o.setWebhookId(credentials.getWebhookId());
        if(credentials.getId() != 0) {
            o.setId(credentials.getId());
        }
        JiraUserCredentialDetails o1 = new JiraUserCredentialDetails();
        if(userCredentials.getId() !=0) {
            o1.setId(userCredentials.getId());
        }
        o1.setJiraCredentials(o);
        o1.setFlockUsername(flockUser);
        o1.setOauthVerifier(userCredentials.getOauthVerifier());
        o1.setAccessToken(userCredentials.getAccessToken());
        o1.setJiraUsername(userCredentials.getJiraUsername());
        o1.setRequestToken(userCredentials.getRequestToken().getToken());
        o1.setRequsttokenSecret(userCredentials.getRequestToken().getTokenSecret());
        db.saveJiraUserCredentials(flockUser, o1);
        userCredentials.setId(o1.getId());
        logger.info("exit saveJiraUserCredentials");
        //jiraUsersMappings.put(flockUser, userCredentials);
    }

    public void saveNewFlockUser(FlockUser flockUser) {
        logger.info("saveNewFlockUser "+flockUser.getFlockUsername());
        FlockUsers o = new FlockUsers();
        o.setFlockUserId(flockUser.getFlockUserId());
        o.setFlockUsername(flockUser.getFlockUsername());
        o.setToken(flockUser.getToken());
        if(flockUser.getId() !=0){
            o.setId(flockUser.getId());
        }
        db.saveNewFlockUser(o);
        flockUser.setId(o.getId());
        logger.info("exit saveNewFlockUser");
        //flockUserMappings.put(flockUser.getFlockUserId(), flockUser);
    }

    public FlockUser getUserFromFlockMappings(String flockUserId) {
        logger.info("getUserFromFlockMappings "+flockUserId);
        FlockUsers o = db.getFlockUserFromUserId(flockUserId);
        FlockUser x = new FlockUser(o.getToken(), o.getFlockUserId(), o.getFlockUsername());
        x.setId(o.getId());
        logger.info("exit getUserFromFlockMappings");
        return x;
        //return flockUserMappings.get(flockUserId);
    }

    public void saveRequestToken(String requestToken, String flockUserId){
        logger.info("saveRequestToken "+requestToken);
        requestTokenMappings.put(requestToken, flockUserId);
        logger.info("exit saveRequestToken");
    }

    public String getFlockUserFromRequestToken(String requestToken){
        logger.info("getFlockUserFromRequestToken "+requestToken);
        return requestTokenMappings.get(requestToken);
    }

    public FlockUser getFlockUserFromJiraUsername(String jiraUsername){
        logger.info("getFlockUserFromJiraUsername "+jiraUsername);
        FlockUsers o = db.getFlockUserFromJiraUsername(jiraUsername);
        if(o == null) {return null;}
        FlockUser x = new FlockUser(o.getToken(), o.getFlockUserId(), o.getFlockUsername());
        x.setId(o.getId());
        logger.info("exit getFlockUserFromJiraUsername");
        return x;
    }
}
