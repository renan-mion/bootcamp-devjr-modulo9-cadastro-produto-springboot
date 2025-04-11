package com.abutua.product_backend.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.models.Product;

@RestController
public class ProductController {
    private List<Category> categories = new CategoryController().getCategories();

    private List<Product> products = Arrays.asList(
        new Product(1, "Produto 1", "Eletrônico", 19.9, categories.get(0).getId(), true, true),
        new Product(2, "Produto 2", "Eletrodoméstico", 29.9, categories.get(1).getId(), true, false),
        new Product(3, "Produto 3", "Produto de limpeza", 39.9, categories.get(2).getId(), false, true));

    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
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
