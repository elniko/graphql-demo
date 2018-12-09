package test.graphql.demo.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test.graphql.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
