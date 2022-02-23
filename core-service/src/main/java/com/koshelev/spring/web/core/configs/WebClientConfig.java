package com.koshelev.spring.web.core.configs;

import com.koshelev.spring.web.core.properties.cart_service_integration.CartServiceIntegrationProperties;
import com.koshelev.spring.web.core.properties.rec_service_integration.RecServiceIntegrationProperties;
import com.koshelev.spring.web.core.properties.cart_service_integration.TimeoutsForCartServiceIntegration;
import com.koshelev.spring.web.core.properties.rec_service_integration.TimeoutsForRecServiceIntegration;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(
        {CartServiceIntegrationProperties.class, RecServiceIntegrationProperties.class,
                TimeoutsForCartServiceIntegration.class, TimeoutsForRecServiceIntegration.class}
)

@RequiredArgsConstructor
public class WebClientConfig {

    private final CartServiceIntegrationProperties cartServiceIntegrationProperties;
    private final RecServiceIntegrationProperties recServiceIntegrationProperties;

    @Bean
    public WebClient cartServiceWebClient(){
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, cartServiceIntegrationProperties.getTimeouts().getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntegrationProperties.getTimeouts().getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntegrationProperties.getTimeouts().getWriteTimeout(), TimeUnit.MILLISECONDS));
                });

        return WebClient
                .builder()
                .baseUrl(cartServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Bean
    public WebClient recServiceWebClient(){
        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, recServiceIntegrationProperties.getTimeouts().getConnectTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(recServiceIntegrationProperties.getTimeouts().getReadTimeout(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(recServiceIntegrationProperties.getTimeouts().getWriteTimeout(), TimeUnit.MILLISECONDS));
                });

        return WebClient
                .builder()
                .baseUrl(recServiceIntegrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
