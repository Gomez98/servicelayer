package org.llamagas.servicelayer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class VaultDebug {

    @Value("${app.secret.key:DEFAULT_SECRET}")
    private String jwtSecret;

    @PostConstruct
    public void printSecret() {
        System.out.println("🔑 JWT_SECRET desde Vault: " + jwtSecret);
    }
}

