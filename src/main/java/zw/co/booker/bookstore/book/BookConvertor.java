package zw.co.booker.bookstore.book;

import org.springframework.stereotype.Component;
import zw.co.booker.bookstore.model.Book;
import zw.co.booker.bookstore.model.Category;
import zw.co.booker.bookstore.model.dto.BookDto;
import zw.co.booker.bookstore.model.dto.CategoryDto;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Component
public class BookConvertor {
    public BookDto toDTO(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setDescription(book.getDescription());
        bookDto.setPrice(book.getPrice());
        bookDto.setCategory(buildCategoryDto(book.getCategory()));
        return bookDto;
    }

    private CategoryDto buildCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
    }

    public Book toEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setPrice(bookDto.getPrice());
        book.setCategory(buildCategory(bookDto.getCategory()));
        return book;
    }

    private Category buildCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
