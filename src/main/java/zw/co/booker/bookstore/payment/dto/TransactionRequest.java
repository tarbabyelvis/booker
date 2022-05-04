package zw.co.booker.bookstore.payment.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

public record TransactionRequest(
        String type,
        String extendedType,
        BigDecimal amount,
        ZonedDateTime created,
        Card card,
        String reference,
        String narration,
        Map<String, Object> additionalData
) {
}
