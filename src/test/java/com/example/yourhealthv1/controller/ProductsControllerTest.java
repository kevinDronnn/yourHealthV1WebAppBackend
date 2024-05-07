package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.dto.CommentDto;
import com.example.yourhealthv1.dto.ProductDto;
import com.example.yourhealthv1.service.ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldSaveProduct() throws Exception{
        ProductDto productDto = new ProductDto("name",0.5f,0.5f,0.5f,0.5f,0.5f);
        when(service.addProduct(any(ProductDto.class))).thenReturn(new ProductDto());

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated());

        verify(service, times(1)).addProduct(any(ProductDto.class));
    }

    @Test
    public void shouldGetProduct() throws Exception{
        when(service.getAllProducts()).thenReturn(List.of(new ProductDto()));

        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllProducts();
    }

    @Test
    public void shouldDeleteProductById() throws Exception{
        doNothing().when(service).deleteProduct(7);

        mockMvc.perform(delete("/api/product/{id}",7)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteProduct(7);
    }




}