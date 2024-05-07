package com.example.yourhealthv1.controller;


import com.example.yourhealthv1.dto.ProductDto;
import com.example.yourhealthv1.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping("/product")
    ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productsService.addProduct(product));
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProductDto());
        }
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
        return ResponseEntity.ok("product with id= "+id+" was delete");
    }
}
