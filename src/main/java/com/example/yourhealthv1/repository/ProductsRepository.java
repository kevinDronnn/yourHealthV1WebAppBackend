package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
}
