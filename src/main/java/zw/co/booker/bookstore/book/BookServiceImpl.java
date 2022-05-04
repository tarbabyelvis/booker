package zw.co.booker.bookstore.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.booker.bookstore.exception.RecordExistException;
import zw.co.booker.bookstore.exception.RecordNotFound;
import zw.co.booker.bookstore.model.Book;
import zw.co.booker.bookstore.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookConvertor convertor;

    @Override
    public Optional<Book> create(BookDto bookDto) {
        if (findByTitle(bookDto.getTitle()).isPresent())
            throw new RecordExistException(String.format("Book with title %s already exists", bookDto.getTitle()));
        Book book = convertor.toEntity(bookDto);
        return Optional.of(repository.save(book));
    }

    @Override
    public Optional<Book> update(BookDto bookDto) {
        findById(bookDto.getId())
                .orElseThrow(() -> new RecordNotFound("Book not found"));
        Book existing = convertor.toEntity(bookDto);
        return Optional.of(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByCategoryTitle(String category) {
        return repository.findByCategoryTitle(category);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
