package org.gluecoders.flock.Impl;

import org.gluecoders.flock.dao.TokenDao;
import org.gluecoders.flock.model.TokenAuthenticatorModel;
import org.gluecoders.flock.rest.TokenAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

/**
 * Created by rajesh_podder on 1/13/2017.
 */
public class TokenAuthenticatorImpl extends TokenAuthenticator {

    private TokenDao tokenDao;

public TokenAuthenticatorImpl(TokenDao tokenDao){this.tokenDao = tokenDao;}

    public void saveToken(TokenAuthenticatorModel tokenAuthenticatorModel) {
        System.out.println("Before DAO");
        System.out.println(tokenAuthenticatorModel.getName());
        System.out.println(tokenAuthenticatorModel.getToken());
        tokenDao.tokenSave(tokenAuthenticatorModel);
    }
}
