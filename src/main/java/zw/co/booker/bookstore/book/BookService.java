package zw.co.booker.bookstore.book;

import zw.co.booker.bookstore.model.Book;
import zw.co.booker.bookstore.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> create(BookDto bookDto);
    Optional<Book> update(BookDto bookDto);
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    List<Book> findAll();
    List<Book> findByCategoryTitle(String category);
    void delete(Long id);
}
