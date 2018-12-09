package test.graphql.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.graphql.demo.model.Cart;
import test.graphql.demo.repo.CartRepository;


@RestController
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    CartRepository cartRepository;

    @GetMapping
    public Iterable<Cart> getAll(){
        return cartRepository.findAll();

    }


}
