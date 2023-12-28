package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Products;
import com.example.yourhealthv1.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public Products getProductById(int id) {
        return productsRepository.getReferenceById(id);
    }
    @Transactional
    public Products addProduct(Products products) {
        return productsRepository.save(products);
    }

    @Transactional
    public void deleteProduct(int id) {
        productsRepository.deleteById(id);
    }
}
