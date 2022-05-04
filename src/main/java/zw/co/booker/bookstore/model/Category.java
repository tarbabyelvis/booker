package zw.co.booker.bookstore.model;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Entity
@Data
public class Category extends BaseEntity {
    private String title;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
