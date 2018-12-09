package test.graphql.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    Long id;
    String title;
    BigDecimal price;
    @Column(columnDefinition = "TEXT")
    String description;
    String sku;
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> images;

}
