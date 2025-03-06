package org.llamagas.servicelayer.helpers;

import org.springframework.http.HttpHeaders;

//@Service
public class RestTemplateHelper {

    // Helper Method to Create Headers
    public HttpHeaders createHeaders(String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "B1SESSION=" + sessionId);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        return headers;
    }
}
