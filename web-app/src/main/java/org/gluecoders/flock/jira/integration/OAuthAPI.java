package org.gluecoders.flock.jira.integration;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.services.HMACSha1SignatureService;
import com.github.scribejava.core.services.RSASha1SignatureService;
import com.github.scribejava.core.services.SignatureService;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class OAuthAPI extends DefaultApi10a {

    private final String jiraBaseUrl;
    private final String requestTokenUrl;
    private final String accessTokenUrl;
    private final String authorizationUrl;
    private final String privateKey;

    public OAuthAPI(String jiraBaseUrl, String privateKey) {
        this.jiraBaseUrl = jiraBaseUrl;
        this.privateKey = privateKey;
        requestTokenUrl = jiraBaseUrl+"/plugins/servlet/oauth/request-token";
        accessTokenUrl = jiraBaseUrl+"/plugins/servlet/oauth/access-token";
        authorizationUrl = jiraBaseUrl+"/plugins/servlet/oauth/authorize";
    }

    @Override
    public String getRequestTokenEndpoint() {
        return requestTokenUrl;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return accessTokenUrl;
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken oAuth1RequestToken) {
        return authorizationUrl+String.format("?oauth_token=%s",oAuth1RequestToken.getToken());
    }

    @Override
    public SignatureService getSignatureService() {
        try {
            KeyFactory fac = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            return new RSASha1SignatureService(fac.generatePrivate(privKeySpec));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new HMACSha1SignatureService();
    }
}
