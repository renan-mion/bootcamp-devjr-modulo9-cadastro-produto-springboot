package com.abutua.product_backend.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.Product;

@RestController
public class ProductController {
    private List<Product> products = Arrays.asList(
        new Product(1, "Produto 1", 19.9),
        new Product(2, "Produto 2", 29.9),
        new Product(3, "Produto 3", 39.9));

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        // if (id <= products.size()) {
        //     return ResponseEntity.ok(products.get(id- 1));
        // } else {
        //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        // }

        Product product = products.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return ResponseEntity.ok(product);
    }

    @GetMapping("products")
    public List<Product> getProducts() {
        return products;
    }
}
