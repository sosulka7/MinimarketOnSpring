package com.koshelev.spring.web.cart.properties.core_service_integration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.core-service")
@Data
public class CoreServiceIntegrationProperties {
    private String url;
    private TimeoutsForCoreServiceIntegration timeouts;
}
