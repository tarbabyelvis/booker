package zw.co.booker.bookstore.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.booker.bookstore.exception.BusinessException;
import zw.co.booker.bookstore.model.Book;
import zw.co.booker.bookstore.model.dto.BookDto;
import zw.co.booker.bookstore.payment.PaymentService;
import zw.co.booker.bookstore.payment.dto.PaymentData;
import zw.co.booker.bookstore.payment.dto.PaymentResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Slf4j
public class BookApi {
    private final BookService service;
    private final BookConvertor convertor;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        log.debug("creating book {}", bookDto);
        Book book = service.create(bookDto)
                .orElseThrow(() -> new BusinessException("Error creating book"));
        bookDto = convertor.toDTO(book);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
        log.debug("updating book {}", bookDto);
        Book book = service.update(bookDto)
                .orElseThrow(() -> new BusinessException("Error updating book"));
        bookDto = convertor.toDTO(book);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        log.debug("Finding book by id {}", id);
        final Optional<Book> bookOptional = service.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookDto converted = convertor.toDTO(book);
            return new ResponseEntity<>(converted, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<BookDto> findByTitle(@PathVariable String title) {
        log.debug("Finding book {}", title);
        final Optional<Book> bookOptional = service.findByTitle(title);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            BookDto converted = convertor.toDTO(book);
            return new ResponseEntity<>(converted, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list/{category}")
    public List<BookDto> findAll(@PathVariable String category) {
        log.debug("Finding all books in category {}", category);
        return service.findByCategoryTitle(category)
                .stream()
                .map(convertor::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/list")
    public List<BookDto> findAll() {
        log.debug("Finding all books");
        return service.findAll()
                .stream()
                .map(convertor::toDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/purchase/{bookTitle}")
    public ResponseEntity<PaymentResponseStatus> purchaseBook(@PathVariable String bookTitle, @RequestBody PaymentData paymentData) {
        log.debug("attempting to make payment for book {} with payment details {}", bookTitle, paymentData);
        PaymentResponseStatus responseStatus = paymentService.pay(paymentData);
        if (responseStatus != PaymentResponseStatus.APPROVED)
            return new ResponseEntity<>(responseStatus, HttpStatus.BAD_REQUEST);
        //Reserve a book and do all the necessary steps for book purchase and save the purchase in the db
        log.debug("Reserving book {} ", bookTitle);
        return new ResponseEntity<>(responseStatus, HttpStatus.OK);
    }
}
