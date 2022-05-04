package zw.co.booker.bookstore.model.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Data
@Builder
public class CategoryDto {
    private Long id;
    private String title;
    private String description;

    @Override
    public String toString() {
        return "CategoryDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
