package test.graphql.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.graphql.demo.model.Product;
import test.graphql.demo.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public List<Product> getProducts(){
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
