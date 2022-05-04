package zw.co.booker.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false, updatable = false)
    private Long id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private LocalDateTime lastModifiedDate;
}
