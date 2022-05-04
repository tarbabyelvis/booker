package zw.co.booker.bookstore.category;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.booker.bookstore.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
}
