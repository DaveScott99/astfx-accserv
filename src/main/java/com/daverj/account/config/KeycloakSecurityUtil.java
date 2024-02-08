package com.daverj.account.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    Keycloak keycloak;

    @Value("${keycloak.client-id}")
    private String adminClientId;

    @Value("${keycloak.client-secret}")
    private String adminClientSecret;

    @Value("${keycloak.domain}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;

    @Bean
    public Keycloak getKeycloakInstance(){

        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(adminClientId)
                    .clientSecret(adminClientSecret)
                    .username(username)
                    .password(password)
                    .build();
        }

        return keycloak;
    }

}
