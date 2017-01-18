package org.gluecoders.flock.jira.integration;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anand_Rajneesh on 1/18/2017.
 */
@Component
public class CProxySelector extends ProxySelector {

    @Override
    public List<Proxy> select(URI uri) {
        InetSocketAddress adr = new InetSocketAddress("10.152.80.42",80);
        return Collections.singletonList(new Proxy(Proxy.Type.HTTP,adr));
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        System.out.println(ioe.getMessage());
    }

    @PostConstruct
    public void after(){
        ProxySelector.setDefault(this);
        System.setProperty("java.net.useSystemProxies", "true");
    }
}
