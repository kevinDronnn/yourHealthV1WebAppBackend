package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.service.RecipesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RecipesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipesService recipesService;

    @Mock
    private Recipes recipes;

    @Mock
    private RecipesProducts recipesProducts;



    @BeforeEach
    void setUp() {
        recipesProducts = new RecipesProducts();
        recipesProducts.setProductName("apple");
        recipesProducts.setGrams(200);
        recipesProducts.setRecipeProductId(1);
        recipesProducts.setRecipes(recipes);

        recipes = new Recipes();
        recipes.setId(2);
        recipes.setName("recipe");
        recipes.setDescription("something");
        recipes.setImageString("some/source");
        recipes.setProducts(List.of(recipesProducts));
    }

    @Test
    void shouldAddRecipe() throws Exception {
        when(recipesService.saveRecipe(recipes)).thenReturn(recipes);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/api/recipe")
                        .file(new MockMultipartFile("recipe_image", "filename.jpg", "image/jpeg", "filecontent".getBytes()))
                        .param("recipe_name", recipes.getName())
                        .param("recipe_description", recipes.getDescription())
                        .param("products_name", recipesProducts.getProductName())
                        .param("products_grams", String.valueOf(recipesProducts.getGrams()))
                        .content(asJsonString(recipes))
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetAllRecipes() throws Exception {
        when(recipesService.getRecipes()).thenReturn(List.of(recipes));

        this.mockMvc.perform(get("/api/recipe"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(recipesService).getRecipes();
    }

    @Test
    void shouldGetRecipeById() throws Exception {
        when(recipesService.getRecipesById(recipes.getId())).thenReturn(recipes);

        this.mockMvc.perform(get("/api/recipe/{id}", recipes.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(recipesService).getRecipesById(recipes.getId());
    }

    @Test
    void shouldDeleteRecipe() throws Exception {
        doNothing().when(recipesService).removeRecipe(recipes.getId());

        this.mockMvc.perform(delete("/api/recipe/{id}", recipes.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(recipesService).removeRecipe(recipes.getId());
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