package zw.co.booker.bookstore.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.booker.bookstore.payment.PaymentService;
import zw.co.booker.bookstore.payment.dto.PaymentData;
import zw.co.booker.bookstore.payment.dto.PaymentResponseStatus;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookApi.class)
@ExtendWith(SpringExtension.class)
class BookApiTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PaymentService paymentService;

       @BeforeEach
       void setUp() {
           PaymentData paymentData = new PaymentData(
                   "Tarbaby Banda",
                   "Book ranamRomeo",
                   BigDecimal.valueOf(10),
                   "1234560000000001",
                   "2024-12-30",
                   new HashMap<>()
           );
           Mockito.when(paymentService.pay(paymentData)).thenReturn(PaymentResponseStatus.APPROVED);
       }
    @Test
    void create() {
    }

    @Test
    void purchaseBook() throws Exception {

    }
}