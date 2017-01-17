package org.gluecoders.flock.dao;

import org.gluecoders.flock.model.TokenAuthenticatorModel;

/**
 * Created by rajesh_podder on 1/16/2017.
 */
public interface TokenDao {

    public void tokenSave(TokenAuthenticatorModel tokenAuthenticatorModel);
}
