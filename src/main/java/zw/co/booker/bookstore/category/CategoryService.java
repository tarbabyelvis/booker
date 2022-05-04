package zw.co.booker.bookstore.category;

import zw.co.booker.bookstore.model.Category;
import zw.co.booker.bookstore.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> create(CategoryDto categoryDto);

    Optional<Category> update(CategoryDto categoryDto);

    Optional<Category> findById(Long id);

    Optional<Category> findByTitle(String id);

    void delete(Long id);

    List<Category> findAll();
}
