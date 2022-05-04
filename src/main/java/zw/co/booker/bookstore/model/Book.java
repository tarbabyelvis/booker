package zw.co.booker.bookstore.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * @Author Tarbaby Elvis Banda on 4/5/2022
 **/
@Entity
@Data
public class Book extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private BigDecimal price;
    @ManyToOne
    private Category category;
}
