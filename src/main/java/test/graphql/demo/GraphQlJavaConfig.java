package test.graphql.demo;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.graphql.demo.model.Cart;
import test.graphql.demo.model.Product;
import test.graphql.demo.repo.CartRepository;
import test.graphql.demo.repo.ProductRepository;

import java.util.List;

@Configuration
public class GraphQlJavaConfig {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;


    @Bean
    GraphQLQueryResolver query(){
        return new GraphQLQueryResolver() {
           public Cart cart(Long id) {
               return  cartRepository.findOne(id);
           }
        };
    }

    @Bean
    public GraphQLResolver<Product> productResolver() {
        return new GraphQLResolver<Product>() {
           public List<String> images(Product product, int limit){
               return product.getImages().subList(0, limit > 0 ? limit : product.getImages().size());
           }
        };
    }



}
