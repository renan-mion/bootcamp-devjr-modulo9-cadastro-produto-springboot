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

import com.abutua.product_backend.models.Category;
import com.abutua.product_backend.models.Product;

@RestController
public class ProductController {
    private List<Category> categories = new CategoryController().getCategories();

    private List<Product> products = new ArrayList<Product>();

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
