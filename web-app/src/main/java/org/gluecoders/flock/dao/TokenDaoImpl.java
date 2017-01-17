package org.gluecoders.flock.dao;

import org.gluecoders.flock.model.TokenAuthenticatorModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rajesh_podder on 1/16/2017.
 */
public class TokenDaoImpl implements TokenDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void tokenSave(TokenAuthenticatorModel tokenAuthenticatorModel) {
        System.out.println("Before SAVE");
        System.out.println(tokenAuthenticatorModel.getToken());
        System.out.println(tokenAuthenticatorModel.getUserId());
        System.out.println(tokenAuthenticatorModel.getName());
        sessionFactory.getCurrentSession().save(tokenAuthenticatorModel);
        System.out.println("SAVE DAO");
    }
}
