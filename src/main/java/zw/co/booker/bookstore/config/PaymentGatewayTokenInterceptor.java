package zw.co.booker.bookstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Component
public class PaymentGatewayTokenInterceptor implements ClientHttpRequestInterceptor {
    @Value("${payment.gateway.token}")
    private String paymentGatewayToken;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        request.getHeaders().setAccept(List.of(MediaType.APPLICATION_JSON));
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", paymentGatewayToken));
        return execution.execute(request, body);
    }
}
