package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Products;
import com.example.yourhealthv1.repository.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ProductsServiceTest {

    private List<Products> productsList = new ArrayList<>();
    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;

    @Mock
    private Products products;

    @BeforeEach
    void setUp() {
        products = new Products();
        products.setId(20);
        products.setName("name");
        products.setCals(22.4f);
        products.setFats(23.3f);
        products.setCarbs(10);
        products.setGrams(100);
        products.setProteins(9);

    }

    @Test
    void shouldGetProducts() {
        when(productsRepository.findAll()).thenReturn(productsList);

        List<Products> result = productsService.getAllProducts();

        verify(productsRepository).findAll();
        assertEquals(productsList, result);
    }

    @Test
    void shouldAddProduct() {
        when(productsRepository.save(products)).thenReturn(products);

        productsService.addProduct(products);

        verify(productsRepository).save(products);
    }

    @Test
    void shouldDeleteProduct() {
        doNothing().when(productsRepository).deleteById(products.getId());

        productsService.deleteProduct(products.getId());

        verify(productsRepository).deleteById(products.getId());
    }
}