package zw.co.booker.bookstore.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private CategoryDto category;

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category.getTitle() +
                '}';
    }
}
