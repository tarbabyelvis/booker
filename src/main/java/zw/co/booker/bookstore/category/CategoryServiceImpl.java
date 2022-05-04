package zw.co.booker.bookstore.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.booker.bookstore.exception.RecordExistException;
import zw.co.booker.bookstore.exception.RecordNotFound;
import zw.co.booker.bookstore.model.Category;
import zw.co.booker.bookstore.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    @Override
    public Optional<Category> create(CategoryDto categoryDto) {
        if(findByTitle(categoryDto.getTitle()).isPresent())
            throw new RecordExistException(String.format("Category with title %s already exists",categoryDto.getTitle()));
        Category category = new Category();
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        return Optional.of(repository.save(category));
    }

    @Override
    public Optional<Category> update(CategoryDto categoryDto) {
        Category existing  = findById(categoryDto.getId())
                .orElseThrow(() -> new RecordNotFound("Category not found"));
        existing.setTitle(categoryDto.getTitle());
        existing.setDescription(categoryDto.getDescription());
        return Optional.of(repository.save(existing));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public void delete(Long id) {
       repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
