package com.example.yourhealthv1.controllers;


import com.example.yourhealthv1.entity.Products;
import com.example.yourhealthv1.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @PostMapping("/product")
    Products addProduct(@RequestBody Products product){
        return productsService.addProduct(product);
    }

    @GetMapping("/products")
    List<Products> getAllProducts(){
        return productsService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
    }
}
