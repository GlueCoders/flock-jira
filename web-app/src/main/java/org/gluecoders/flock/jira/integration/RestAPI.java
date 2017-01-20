package org.gluecoders.flock.jira.integration;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class RestAPI {

    public OAuthRequest assignIssueRequest(String baseUrl, String issueId, String assignee){
        OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl+String.format("/rest/api/2/issue/%s/assignee",issueId));
        request.setPayload(new JSONObject(Collections.singletonMap("name",assignee)).toString());
        return request;
    }

    public OAuthRequest addCommentRequest(String baseUrl, String issueId, String comment) throws JSONException {
        OAuthRequest request = new OAuthRequest(Verb.POST, baseUrl+String.format("/rest/api/2/issue/%s/comment",issueId));
        JSONObject json = new JSONObject();
        json.put("body",comment);
        JSONObject visibility = new JSONObject();
        /*visibility.put("type","role");
        visibility.put("value","jira-developers");
        json.put("visibility", visibility);*/
        request.setPayload(json.toString());
        request.addHeader("Content-type","application/json");
        return request;
    }

    //public OAuthRequest addWorklogRequest(String baseUrl, String issueId, String )

    public OAuthRequest getSelf(String baseUrl){
        return new OAuthRequest(Verb.GET, baseUrl+"/rest/api/2/myself");
    }

}
