package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Products;
import com.example.yourhealthv1.service.ProductsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

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
    void shouldGetProducts() throws Exception {
        when(productsService.getAllProducts()).thenReturn(List.of(products));

        this.mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldSaveProduct() throws Exception {
        when(productsService.addProduct(products)).thenReturn(products);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/product")
                        .content(asJsonString(products))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        doNothing().when(productsService).deleteProduct(products.getId());

        this.mockMvc.perform(delete("/api/product/{id}", products.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(productsService).deleteProduct(products.getId());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}