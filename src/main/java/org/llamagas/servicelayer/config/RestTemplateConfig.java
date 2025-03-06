package org.llamagas.servicelayer.config;

import org.springframework.web.client.RestTemplate;

//@Configuration
public class RestTemplateConfig {

    //@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
