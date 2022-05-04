package zw.co.booker.bookstore.payment.dto;

import java.time.LocalDate;

public record Card(
        String id,
        LocalDate expiry
) {
}
