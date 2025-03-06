package org.llamagas.servicelayer.config;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class SapConfig {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "https://<sap-service-layer-url>:50000/b1s/v1";
    private final String USERNAME = "manager";
    private final String PASSWORD = "password";
    private final String COMPANY_DB = "SAPDB";


    SapConfig(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticate() {
        String loginUrl = BASE_URL + "/Login";

        Map<String, String> loginPayload = new HashMap<>();
        loginPayload.put("UserName", USERNAME);
        loginPayload.put("Password", PASSWORD);
        loginPayload.put("CompanyDB", COMPANY_DB);

        Map<String, Object> response = restTemplate.postForObject(loginUrl, loginPayload, Map.class);
        return (String) response.get("SessionId");
    }
}
