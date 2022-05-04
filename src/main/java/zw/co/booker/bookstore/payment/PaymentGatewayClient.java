package zw.co.booker.bookstore.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zw.co.booker.bookstore.payment.dto.TransactionRequest;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentGatewayClient {
    @Value("${payment.gateway.baseUrl}")
    private String gatewayUrl;
    private final RestTemplate restTemplate;

    public TransactionResponse paymentRequest(TransactionRequest request) {
        log.debug("Sending transaction request {}", request);
        return restTemplate.postForObject(String.format("%s/api/transaction", gatewayUrl), request, TransactionResponse.class);
    }
}
