package zw.co.booker.bookstore.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {
    @Value("${payment.gateway.connectionTimeOut}")
    private Long connectionTimeOut;
    @Value("${payment.gateway.connectionReadTiemout}")
    private Long connectionReadTiemout;
    private final PaymentGatewayTokenInterceptor tokenInterceptor;

    @Bean
    public RestTemplate restTemplate() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        messageConverters.add(converter);
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectionTimeOut))
                .messageConverters(messageConverters)
                .setReadTimeout(Duration.ofMillis(connectionReadTiemout))
                .interceptors(tokenInterceptor)
                .build();
    }
}
