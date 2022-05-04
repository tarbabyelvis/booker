package zw.co.booker.bookstore.payment.dto;

import java.math.BigDecimal;
import java.util.Map;

public record PaymentData(
        String buyer,
        String bookTitle,
        BigDecimal amount,
        String cardId,
        String cardExpiry,
        Map<String, Object> additionalInfo
) {
}
