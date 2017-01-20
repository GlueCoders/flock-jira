package org.gluecoders.flock.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Entity
@Table(name = "JIRA_CREDENTIALS")
public class JiraCredentialDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "BASE_URL")
    private String baseUrl;
    @Column(name = "WEBHOOK_ID")
    private String webhookId;
    @Column(name = "PRIVATE_KEY")
    private String privateKey;
    @Column(name = "CONSUMER_KEY")
    private String consumerKey;
    @Column(name = "PUBLIC _KEY")
    private String publicKey;
    @OneToMany(mappedBy = "jiraCredentials")
    private List<JiraUserCredentialDetails> jiraUserCredentialsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }


    public List<JiraUserCredentialDetails> getJiraUserCredentialsList() {
        return jiraUserCredentialsList;
    }

    public void setJiraUserCredentialsList(List<JiraUserCredentialDetails> jiraUserCredentialsList) {
        this.jiraUserCredentialsList = jiraUserCredentialsList;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
