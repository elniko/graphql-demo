package test.graphql.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.graphql.demo.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
