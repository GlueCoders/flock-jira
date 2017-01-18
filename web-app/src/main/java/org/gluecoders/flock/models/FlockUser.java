package org.gluecoders.flock.models;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class FlockUser {

    private String token;
    private String flockUserId;
    private String flockUsername;

    public FlockUser(String token, String flockUserId, String flockUsername) {
        this.token = token;
        this.flockUserId = flockUserId;
        this.flockUsername = flockUsername;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFlockUserId() {
        return flockUserId;
    }

    public void setFlockUserId(String flockUserId) {
        this.flockUserId = flockUserId;
    }

    public String getFlockUsername() {
        return flockUsername;
    }

    public void setFlockUsername(String flockUsername) {
        this.flockUsername = flockUsername;
    }
}
