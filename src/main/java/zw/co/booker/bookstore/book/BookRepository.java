package zw.co.booker.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.booker.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByCategoryTitle(String categoryTitle);
}
