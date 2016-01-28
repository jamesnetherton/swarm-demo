package com.jamesnetherton;

import org.wildfly.swarm.config.management.SecurityRealm;
import org.wildfly.swarm.config.management.security_realm.SslServerIdentity;
import org.wildfly.swarm.config.undertow.server.HttpsListener;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.management.ManagementFraction;
import org.wildfly.swarm.undertow.UndertowFraction;

public class Main {
    public static void main(String... args) throws Exception {
        Container container = new Container();

        UndertowFraction undertowFraction = UndertowFraction.createDefaultFraction();
        undertowFraction.server("ssl-server", server -> server.httpsListener(new HttpsListener("https")
            .securityRealm("SSLRealm")
            .socketBinding("https")
        ));

        container.fraction(ManagementFraction.createDefaultFraction()
            .securityRealm(new SecurityRealm("SSLRealm")
                .sslServerIdentity(new SslServerIdentity<>()
                    .keystorePath("/path/to/keystore.ks")
                    .keystorePassword("password")
                    .alias("my-alias")
                )
            )
        );

        container.fraction(UndertowFraction.createDefaultFraction()
            .server("ssl-server", server -> server.httpsListener(new HttpsListener("https")
                    .securityRealm("SSLRealm")
                    .socketBinding("https")
                )
            ));

        container.start();
    }
}
