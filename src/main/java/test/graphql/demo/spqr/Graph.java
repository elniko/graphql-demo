package test.graphql.demo.spqr;


import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.graphql.demo.model.Cart;
import test.graphql.demo.model.Product;
import test.graphql.demo.repo.CartRepository;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.List;

@Component
public class Graph {

    @Autowired
    CartRepository cartRepository;

    @GraphQLQuery(name = "cart")
    public Cart cart(@GraphQLArgument(name="id") Long id){
        return cartRepository.findById(id).get();
    }

    @GraphQLQuery(name="images")
    public List<String> images(@GraphQLContext Product product,
                               @GraphQLArgument(name= "limit", defaultValue = "0") int limit){
        return product.getImages().subList(0, limit > 0 ? limit : product.getImages().size());
    }

}
