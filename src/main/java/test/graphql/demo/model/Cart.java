package test.graphql.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Item> items =  new ArrayList<>();

    public BigDecimal getSubTotal() {
        return getItems().stream()
                .map(Item::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addProduct(Product product, Integer quantity) {
        Item item = items.stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElseGet(() -> {
                    Item newItem = new Item();
                    newItem.setProduct(product);
                    items.add(newItem);
                    return newItem;
                });

        item.setQuantity(item.getQuantity() + quantity);
        item.setTotal(product.price.multiply(BigDecimal.valueOf(item.getQuantity())));
    }

    @Embeddable
    @Data
    public static class Item {

        @OneToOne
        Product product;
        Integer quantity = 0;
        BigDecimal total;
    }

}
