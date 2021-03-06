package org.gluecoders.flock.models;

import java.io.Serializable;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class FlockUser implements Serializable{

    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
