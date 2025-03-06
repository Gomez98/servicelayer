package org.llamagas.servicelayer.service;

import org.llamagas.servicelayer.config.SapConfig;
import org.llamagas.servicelayer.helpers.RestTemplateHelper;
import org.llamagas.servicelayer.model.domain.MasterFields;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
public class SapService {

    private final SapConfig sapConfig;
    private final RestTemplate restTemplate;
    private final RestTemplateHelper restTemplateHelper;

    private final String BASE_URL = "https://<sap-service-layer-url>:50000/b1s/v1";

    SapService(SapConfig sapConfig, RestTemplate restTemplate, RestTemplateHelper restTemplateHelper) {
        this.sapConfig = sapConfig;
        this.restTemplate = restTemplate;
        this.restTemplateHelper = restTemplateHelper;
    }

    // Marketing Data Methods
    public void registerMarketingData(MasterFields masterFields) {
        String sessionId = sapConfig.authenticate();
        String url = BASE_URL + "/UDT_MarketingData";

        HttpHeaders headers = restTemplateHelper.createHeaders(sessionId);

        Map<String, Object> payload = new HashMap<>();
        payload.put("name", masterFields.getName());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        restTemplate.postForEntity(url, entity, String.class);
    }

    public List<MasterFields> getAllMarketingData() {
        String sessionId = sapConfig.authenticate();
        String url = BASE_URL + "/UDT_MarketingData";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplateHelper.createHeaders(sessionId);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<MasterFields[]> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, MasterFields[].class);
        return Arrays.asList(response.getBody());
    }

    public MasterFields getMarketingDataById(String id) {
        String sessionId = sapConfig.authenticate();
        String url = BASE_URL + "/UDT_MarketingData('" + id + "')";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = restTemplateHelper.createHeaders(sessionId);

        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<MasterFields> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, entity, MasterFields.class);
        return response.getBody();
    }


}
