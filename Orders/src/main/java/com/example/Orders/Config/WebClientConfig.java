package com.example.Orders.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean WebClient inventoryClient(){
        return webClientBuilder().baseUrl("http://localhost:8080/api/v1/inventory").build();
    }

    @Bean WebClient productClient(){
        return webClientBuilder().baseUrl("http://localhost:8082/api/v1/products").build();
    }
}
