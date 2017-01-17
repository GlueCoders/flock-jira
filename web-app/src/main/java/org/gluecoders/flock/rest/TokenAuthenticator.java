package org.gluecoders.flock.rest;

import org.gluecoders.flock.Impl.TokenAuthenticatorImpl;
import org.gluecoders.flock.model.TokenAuthenticatorModel;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Satish_Bhuria on 1/13/2017.
 */
@Component
@Path("/service")
public class TokenAuthenticator {


    private TokenAuthenticatorImpl tokenImpl;

    public TokenAuthenticator(){}

    public TokenAuthenticator(TokenAuthenticatorImpl tokenImpl){
        this.tokenImpl = tokenImpl;
    }

    @POST
    @Path("/authentication")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tokenAuthenticator(TokenAuthenticatorModel tokenAuthenticatorModel) {

        //tokenAuthenticatorModel.setName("RP");
       // tokenAuthenticatorModel.setUserId("RP100");
       // tokenAuthenticatorModel.setToken("ABCDEF");
        System.out.println("Before");
        tokenImpl.saveToken(tokenAuthenticatorModel);
        return Response.status(200).entity("Authentication call").build();

    }

}
