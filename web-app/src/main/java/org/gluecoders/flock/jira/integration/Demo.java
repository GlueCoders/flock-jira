package org.gluecoders.flock.jira.integration;

import org.gluecoders.flock.dao.Database;
import org.gluecoders.flock.models.JiraCredentials;
import org.gluecoders.flock.models.JiraUserCredentials;

import java.net.ProxySelector;
import java.util.Scanner;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class Demo {

    public static void main(String[] args) {
        Onboard onboard = new Onboard();
        try {
            ProxySelector.setDefault(new CProxySelector());
            System.setProperty("java.net.useSystemProxies", "true");
            Scanner in = new Scanner(System.in);
            String lfock = "anandrajneesh@outlook.com";
            JiraCredentials jiraCredentials = onboard.onboardUserNewJira(lfock,"https://demojira12.atlassian.net");
            System.out.println(jiraCredentials);
            String tmp = in.nextLine();
            String authurl = onboard.onboardUser(lfock,"https://demojira12.atlassian.net");
            System.out.println(authurl);
            String code  = in.nextLine();
            onboard.verifyUser(lfock,code);
            Database db = new Database();
            JiraUserCredentials jiraUserCredentials = db.getJiraUserCredentials(lfock);
            System.out.println(
                   jiraUserCredentials
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
