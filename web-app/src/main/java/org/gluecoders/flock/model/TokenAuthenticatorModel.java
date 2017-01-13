package org.gluecoders.flock.model;

/**
 * Created by Satish_Bhuria on 1/13/2017.
 */
public class TokenAuthenticatorModel {

    private String name;
    private String userId;
    private String token;


    public TokenAuthenticatorModel() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
