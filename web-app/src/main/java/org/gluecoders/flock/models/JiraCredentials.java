package org.gluecoders.flock.models;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class JiraCredentials {

    private String baseUrl;
    private String webhookId;
    private String privateKey;
    private String consumerKey;
    private String publicKey;

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

    public JiraCredentials(String baseUrl, String webhookId, String privateKey, String consumerKey, String publicKey) {
        this.baseUrl = baseUrl;
        this.webhookId = webhookId;
        this.privateKey = privateKey;
        this.consumerKey = consumerKey;
        this.publicKey = publicKey;
    }

    @Override
    public String toString() {
        return "JiraCredentials{" +
                "baseUrl='" + baseUrl + '\'' +
                ", webhookId='" + webhookId + '\'' +
                ", privateKey='" + privateKey + '\'' +
                ", consumerKey='" + consumerKey + '\'' +
                ", publicKey='" + publicKey + '\'' +
                '}';
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
