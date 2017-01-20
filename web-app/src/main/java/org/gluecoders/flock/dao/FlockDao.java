package org.gluecoders.flock.dao;

import org.gluecoders.flock.model.FlockUsers;
import org.gluecoders.flock.model.JiraCredentialDetails;
import org.gluecoders.flock.model.JiraUserCredentialDetails;

/**
 * Created by rajesh_podder on 1/19/2017.
 */
 interface FlockDao {

     void saveNewFlockUser(FlockUsers flockUser);
     JiraCredentialDetails getJiraCredentials(String jiraBaseUrl);
     void saveJiraCredentials(JiraCredentialDetails jiraCredentials);
     void saveJiraUserCredentials(String flockUsername, JiraUserCredentialDetails jiraUserCredentials);
     JiraUserCredentialDetails getJiraUserCredentials(String flockUser);
    // String getFlockUserFromRequestToken(String requestToken);
}
