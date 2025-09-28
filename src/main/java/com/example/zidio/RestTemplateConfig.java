package com.example.zidio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for defining the RestTemplate bean.
 */
// Suppress warning if not used yet
@Configuration
public class RestTemplateConfig {

    /**
     * Defines a RestTemplate bean for making HTTP requests.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
