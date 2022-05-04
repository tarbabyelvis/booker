package zw.co.booker.bookstore.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.booker.bookstore.exception.BusinessException;
import zw.co.booker.bookstore.payment.dto.Card;
import zw.co.booker.bookstore.payment.dto.PaymentData;
import zw.co.booker.bookstore.payment.dto.PaymentResponseStatus;
import zw.co.booker.bookstore.payment.dto.TransactionRequest;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentGatewayClient client;

    @Override
    public PaymentResponseStatus pay(PaymentData paymentData) {
        TransactionRequest request = buildTransactionRequest(paymentData);
        TransactionResponse response = client.paymentRequest(request);
        if (response == null)
            throw new BusinessException("Invalid response from gateway");
        log.debug("payment response {}", response);
        return switch (response.responseCode()) {
            case "000" -> PaymentResponseStatus.APPROVED;
            case "001" -> PaymentResponseStatus.CONTACT_OWNER;
            case "005" -> PaymentResponseStatus.DO_NOT_HONOUR;
            case "012" -> PaymentResponseStatus.INVALID_TRANSACTION;
            case "096" -> PaymentResponseStatus.SYSTEM_MALFUNCTION;
            default -> throw new BusinessException("Unknown response code");
        };
    }

    private TransactionRequest buildTransactionRequest(PaymentData paymentData) {
        return new TransactionRequest(
                "PURCHASE",
                "NONE",
                paymentData.amount(),
                ZonedDateTime.now(),
                new Card(
                        paymentData.cardId(),
                        formatExpiryDate(paymentData.cardExpiry())
                ),
                generateReference(),
                String.format("Purchase a book %s for %s", paymentData.bookTitle(), paymentData.buyer()),
                paymentData.additionalInfo()
        );
    }

    private LocalDate formatExpiryDate(String expiryDate) {
        return LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH));
    }

    private String generateReference() {
        return UUID.randomUUID().toString();
    }
}
