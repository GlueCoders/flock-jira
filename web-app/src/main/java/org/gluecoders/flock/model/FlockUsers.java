package org.gluecoders.flock.model;

import javax.persistence.*;

@Entity
@Table(name = "FLOCK_USERS")
public class FlockUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "USERID")
    private String flockUserId;
    @Column(name = "USERNAME")
    private String flockUsername;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
