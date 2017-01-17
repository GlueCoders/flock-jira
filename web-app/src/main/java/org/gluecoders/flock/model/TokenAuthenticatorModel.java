package org.gluecoders.flock.model;

import javax.persistence.*;

/**
 * Created by Satish_Bhuria on 1/13/2017.
 */
@Entity
@Table(name = "FLOCK_USER")
public class TokenAuthenticatorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USERID")
    private String userId;
    @Column(name = "TOKEN")
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
