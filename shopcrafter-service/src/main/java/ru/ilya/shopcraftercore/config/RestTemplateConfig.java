package ru.ilya.shopcraftercore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${yookassa.auth-token}")
    private String yookasaToken;


    @Bean
    public RestTemplate yookassaRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Basic " + yookasaToken);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
}
