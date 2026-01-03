package com.microservices.lab.bookservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class PricingClient {

    private static final Logger logger = LoggerFactory.getLogger(PricingClient.class);

    private final RestTemplate restTemplate;
    private final String pricingServiceUrl;

    public PricingClient(
            @Value("${pricing.service.url:http://pricing-service:8082}") String pricingServiceUrl) {
        this.pricingServiceUrl = pricingServiceUrl;
        this.restTemplate = new RestTemplate();
        // Set timeouts: connect timeout 1s, read timeout 2s
        org.springframework.http.client.SimpleClientHttpRequestFactory factory =
                new org.springframework.http.client.SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(1000);
        factory.setReadTimeout(2000);
        this.restTemplate.setRequestFactory(factory);
    }

    @Retry(name = "pricingClient")
    @CircuitBreaker(name = "pricingClient", fallbackMethod = "fallbackPrice")
    public Double getPrice(Long bookId) {
        logger.info("Calling pricing service for bookId: {}", bookId);
        String url = pricingServiceUrl + "/api/prices/" + bookId;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> body = response.getBody();
        if (body != null && body.containsKey("price")) {
            Object priceObj = body.get("price");
            if (priceObj instanceof Number) {
                return ((Number) priceObj).doubleValue();
            }
        }
        throw new IllegalStateException("Invalid response from pricing service");
    }

    // Fallback method with EXACT signature of original method PLUS Throwable argument
    public Double fallbackPrice(Long bookId, Throwable throwable) {
        logger.warn("Pricing service call failed for bookId: {}. Using fallback price: 0.0. Error: {}",
                bookId, throwable.getMessage());
        return 0.0;
    }
}

