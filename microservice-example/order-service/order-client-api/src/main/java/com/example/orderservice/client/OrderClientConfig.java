package com.example.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.orderservice.client.api.OrderApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty("order-api.host")
public class OrderClientConfig {

    @Value("${order-api.host}")
    private final String orderApiHost;

    @Bean
    public OrderApi orderApi() {
        log.info("Create order api to host: {}", orderApiHost);

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(orderApiHost);

        return apiClient.buildClient(OrderApi.class);
    }

}
