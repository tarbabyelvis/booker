package zw.co.booker.bookstore.payment;

import java.time.ZonedDateTime;

public record TransactionResponse(
        ZonedDateTime updated,
        String responseCode,
        String responseDescription,
        String reference,
        String debitReference
) {
}
