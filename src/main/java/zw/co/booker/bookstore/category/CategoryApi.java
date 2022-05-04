package zw.co.booker.bookstore.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.booker.bookstore.exception.BusinessException;
import zw.co.booker.bookstore.model.Category;
import zw.co.booker.bookstore.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@RestController
@Slf4j
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        log.debug("creating category {}", categoryDto);
        Category category = service.create(categoryDto)
                .orElseThrow(() -> new BusinessException("Error creating category"));
        categoryDto = CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto) {
        log.debug("updating category {}", categoryDto);
        Category category = service.update(categoryDto)
                .orElseThrow(() -> new BusinessException("Error updating category"));
        categoryDto = CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .build();
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable long id) {
        log.debug("Finding category {}", id);
        final Optional<Category> categoryOptional = service.findById(id);
        if (categoryOptional.isPresent()) {
            Category foundCategory = categoryOptional.get();
            CategoryDto converted = CategoryDto.builder()
                    .id(id)
                    .title(foundCategory.getTitle())
                    .description(foundCategory.getDescription())
                    .build();
            return new ResponseEntity<>(converted, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByTitle/{title}")
    public ResponseEntity<CategoryDto> findByTitle(@PathVariable String title) {
        log.debug("Finding category {}", title);
        final Optional<Category> categoryOptional = service.findByTitle(title);
        if (categoryOptional.isPresent()) {
            Category foundCategory = categoryOptional.get();
            CategoryDto converted = CategoryDto.builder()
                    .id(foundCategory.getId())
                    .title(foundCategory.getTitle())
                    .description(foundCategory.getDescription())
                    .build();
            return new ResponseEntity<>(converted, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public List<CategoryDto> findAll() {
        log.debug("Finding all categories");
        return service.findAll()
                .stream()
                .map(category -> CategoryDto.builder()
                        .id(category.getId())
                        .title(category.getTitle())
                        .description(category.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
