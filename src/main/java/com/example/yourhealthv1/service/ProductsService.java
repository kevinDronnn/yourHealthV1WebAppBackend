package com.example.yourhealthv1.service;

import com.example.yourhealthv1.controller.AdvicesController;
import com.example.yourhealthv1.dto.ProductDto;
import com.example.yourhealthv1.entity.Products;
import com.example.yourhealthv1.mapper.ProductMapper;
import com.example.yourhealthv1.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductDto> getAllProducts() {
        return ProductMapper.INSTANCE.productsEntityToProductDto(productsRepository.findAll());
    }

    @Transactional
    public ProductDto addProduct(ProductDto products) {
        Optional<ProductDto> product = Optional.ofNullable(products);
        if (product.isPresent()){
            productsRepository.save(ProductMapper.INSTANCE.productDtoToProductsEntity(product.get()));
            logger.info("Products was added: " + products.getName());
            return product.get();
        }else {
            throw new NullPointerException("some of fields are null");
        }
    }

    @Transactional
    public void deleteProduct(int id) {
        productsRepository.deleteById(id);
        logger.info("Product with id="+id+" was deleted");
    }
}
