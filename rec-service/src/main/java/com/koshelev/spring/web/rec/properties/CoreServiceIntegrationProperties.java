package com.koshelev.spring.web.rec.properties;

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
