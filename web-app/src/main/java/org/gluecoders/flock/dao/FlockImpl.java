package org.gluecoders.flock.dao;

import org.gluecoders.flock.model.FlockUsers;
import org.gluecoders.flock.model.JiraCredentialDetails;
import org.gluecoders.flock.model.JiraUserCredentialDetails;
import org.gluecoders.flock.models.JiraUserCredentials;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rajesh_podder on 1/19/2017.
 */
@Component
public class FlockImpl implements FlockDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveNewFlockUser(FlockUsers flockUser) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        s.save(flockUser);
        t.commit();

    }

    public JiraCredentialDetails getJiraCredentials(String jiraBaseUrl) {
        //JiraCredentials jiraCredentials =  (JiraCredentials) sessionFactory.getCurrentSession().get(JiraCredentials.class, jiraBaseUrl);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JiraCredentialDetails.class);
        JiraCredentialDetails jiraCredentials = (JiraCredentialDetails) criteria.add(Restrictions.eq("baseUrl", jiraBaseUrl)).uniqueResult();
        return jiraCredentials;
    }

    public void saveJiraCredentials(JiraCredentialDetails jiraCredentials) {
        sessionFactory.getCurrentSession().save(jiraCredentials);
    }

    public void saveJiraUserCredentials(String flockUsername, JiraUserCredentialDetails jiraUserCredentials) {
       /* Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JiraUserCredentialDetails.class);
        JiraUserCredentialDetails jiraUserCredentialDetails = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("flockUsername", flockUsername)).uniqueResult();
        if (jiraUserCredentialDetails != null) {*/
        sessionFactory.getCurrentSession().save(jiraUserCredentials);
        // }
    }

    public JiraUserCredentialDetails getJiraUserCredentials(String flockUser) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JiraUserCredentials.class);
        JiraUserCredentialDetails jiraUserCredentials = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("flockUsername", flockUser)).uniqueResult();
        return jiraUserCredentials;
    }

   /* public String getFlockUserFromRequestToken(String requestToken) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("token", requestToken)).uniqueResult();
        return flockUsers.getFlockUserId();
    }*/

    public FlockUsers getFlockUserFromUsername(String flockUsername) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("flockUsername", flockUsername)).uniqueResult();
        return flockUsers;
    }

    public FlockUsers getFlockUserFromUserId(String flockUserId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("flockUserId", flockUserId)).uniqueResult();
        return flockUsers;
    }

    public FlockUsers getFlockUserFromJiraUsername(String jiraUsername) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(JiraUserCredentialDetails.class);
        JiraUserCredentialDetails o = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("jiraUsername", jiraUsername)).uniqueResult();
        if (o != null) {
            return getFlockUserFromUserId(o.getFlockUsername());
        }
        return null;
    }

}
