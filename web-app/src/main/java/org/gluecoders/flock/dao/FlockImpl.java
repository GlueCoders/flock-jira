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
import org.springframework.transaction.annotation.Transactional;

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
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        Criteria criteria = s.createCriteria(JiraCredentialDetails.class);
        JiraCredentialDetails jiraCredentials = (JiraCredentialDetails) criteria.add(Restrictions.eq("baseUrl", jiraBaseUrl)).uniqueResult();
        t.commit();
        return jiraCredentials;
    }
    
    public void saveJiraCredentials(JiraCredentialDetails jiraCredentials) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        s.save(jiraCredentials);
        t.commit();
    }
    public void saveJiraUserCredentials(String flockUsername, JiraUserCredentialDetails jiraUserCredentials) {
       /* Criteria criteria = s.createCriteria(JiraUserCredentialDetails.class);
        JiraUserCredentialDetails jiraUserCredentialDetails = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("flockUsername", flockUsername)).uniqueResult();
        if (jiraUserCredentialDetails != null) {*/
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        s.save(jiraUserCredentials);
        t.commit();
        // }
    }
    public JiraUserCredentialDetails getJiraUserCredentials(String flockUser) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        Criteria criteria = s.createCriteria(JiraUserCredentials.class);
        JiraUserCredentialDetails jiraUserCredentials = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("flockUsername", flockUser)).uniqueResult();
        t.commit();
        return jiraUserCredentials;
    }

   /* public String getFlockUserFromRequestToken(String requestToken) {
        Criteria criteria = s.createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("token", requestToken)).uniqueResult();
        return flockUsers.getFlockUserId();
    }*/

    public FlockUsers getFlockUserFromUsername(String flockUsername) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        Criteria criteria = s.createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("flockUsername", flockUsername)).uniqueResult();
        t.commit();
        return flockUsers;
    }
    public FlockUsers getFlockUserFromUserId(String flockUserId) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        Criteria criteria = s.createCriteria(FlockUsers.class);
        FlockUsers flockUsers = (FlockUsers) criteria.add(Restrictions.eq("flockUserId", flockUserId)).uniqueResult();
        t.commit();
        return flockUsers;
    }
    public FlockUsers getFlockUserFromJiraUsername(String jiraUsername) {
        Session s = sessionFactory.getCurrentSession();
        Transaction t = s.beginTransaction();
        Criteria criteria = s.createCriteria(JiraUserCredentialDetails.class);
        JiraUserCredentialDetails o = (JiraUserCredentialDetails) criteria.add(Restrictions.eq("jiraUsername", jiraUsername)).uniqueResult();
        t.commit();
        if (o != null) {
            return getFlockUserFromUserId(o.getFlockUsername());
        }
        return null;
    }

}
