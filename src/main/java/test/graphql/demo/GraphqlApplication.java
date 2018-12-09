package test.graphql.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import test.graphql.demo.model.Cart;
import test.graphql.demo.model.Product;
import test.graphql.demo.repo.CartRepository;
import test.graphql.demo.repo.ProductRepository;

@SpringBootApplication
@Slf4j
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Autowired
    @Bean
    CommandLineRunner commandLineRunner(ObjectMapper mapper, ProductRepository productRepository, CartRepository cartRepository) {
        return (args) -> {
            ClassLoader classLoader = getClass().getClassLoader();
            JsonNode jsonNode = mapper.readTree(classLoader.getResourceAsStream("products.json"));
            Product[] products = mapper.treeToValue(jsonNode, Product[].class);

            Cart cart = new Cart();
            for(Product product : products){
                Product sp = productRepository.save(product);
                cart.addProduct(sp, 1);
                log.warn("Product id:" + sp.getId());
            }

            Cart sc = cartRepository.save(cart);

            log.warn("Cart id: " + sc.getId());
        };

    }

}
