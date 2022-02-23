package com.koshelev.spring.web.core.properties.rec_service_integration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.rec-service")
@Data
public class RecServiceIntegrationProperties {
    private String url;
    private TimeoutsForRecServiceIntegration timeouts;

}
