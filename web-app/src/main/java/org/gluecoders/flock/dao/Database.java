package org.gluecoders.flock.dao;

import org.gluecoders.flock.models.FlockUser;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public final class Database {

    private final Map<String, JiraCredentials> jiraMappings = new ConcurrentHashMap<String, JiraCredentials>();
    private final Map<String, JiraUserCredentials> jiraUsersMappings = new ConcurrentHashMap<String, JiraUserCredentials>();
    private final Map<String, FlockUser> flockUserMappings = new ConcurrentHashMap<String, FlockUser>();
    private final Map<String, String> requestTokenMappings = new ConcurrentHashMap<String, String>();

    public JiraUserCredentials getJiraUserCredentials(String flockUser) {
        return jiraUsersMappings.get(flockUser);
    }

    public JiraCredentials getJiraCredentials(String jiraUrl) {
        return jiraMappings.get(jiraUrl);
    }

    public void saveJiraCredentials(JiraCredentials credentials) {
        jiraMappings.put(credentials.getBaseUrl(), credentials);
    }

    public void saveJiraUserCredentials(String flockUser, JiraUserCredentials userCredentials) {
        jiraUsersMappings.put(flockUser, userCredentials);
    }

    public void saveNewFlockUser(FlockUser flockUser) {
        flockUserMappings.put(flockUser.getFlockUserId(), flockUser);
    }

    public FlockUser getUserFromFlockMappings(String flockUserId) {
        return flockUserMappings.get(flockUserId);
    }

    public void saveRequestToken(String requestToken, String flockUserId){
        requestTokenMappings.put(requestToken, flockUserId);
    }

    public String getFlockUserFromRequestToken(String requestToken){
        return requestTokenMappings.get(requestToken);
    }

    public FlockUser getFlockUserFromJiraUsername(String jiraUsername){
        return null;
    }
}
