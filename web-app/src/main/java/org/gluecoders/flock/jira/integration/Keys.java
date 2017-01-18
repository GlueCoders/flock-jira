package org.gluecoders.flock.jira.integration;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
public class Keys {

    private final String publicKey;
    private final String privateKey;

    public Keys(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public static Keys generate() throws Exception {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            return new Keys(Base64.encode(pair.getPublic().getEncoded()),Base64.encode(pair.getPrivate().getEncoded()));
        }catch (Exception e){
            throw e;
        }
    }
}
