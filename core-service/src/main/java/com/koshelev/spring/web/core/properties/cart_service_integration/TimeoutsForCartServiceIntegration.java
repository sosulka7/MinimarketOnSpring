package com.koshelev.spring.web.core.properties.cart_service_integration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.cart-service.timeouts")
@Data
public class TimeoutsForCartServiceIntegration {
    private Integer readTimeout;
    private Integer writeTimeout;
    private Integer connectTimeout;
}
