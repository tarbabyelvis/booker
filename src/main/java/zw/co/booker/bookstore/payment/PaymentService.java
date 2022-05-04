package zw.co.booker.bookstore.payment;

import zw.co.booker.bookstore.payment.dto.PaymentData;
import zw.co.booker.bookstore.payment.dto.PaymentResponseStatus;

public interface PaymentService {
    PaymentResponseStatus pay(PaymentData paymentData);
}
