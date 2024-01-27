package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.repository.RecipesRepository;
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
class RecipesServiceTest {
    private List<Recipes> recipesList = new ArrayList<>();
    @InjectMocks
    private RecipesService recipesService;

    @Mock
    private RecipesRepository recipesRepository;

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
    void shouldGetRecipes() {
        when(recipesRepository.findAll()).thenReturn(recipesList);

        List<Recipes> result = recipesService.getRecipes();

        verify(recipesRepository).findAll();
        assertEquals(result,recipesList);
    }

    @Test
    void shouldGetRecipesById() {
        when(recipesRepository.getReferenceById(recipes.getId())).thenReturn(recipes);

        Recipes result = recipesService.getRecipesById(recipes.getId());

        verify(recipesRepository).getReferenceById(recipes.getId());
        assertEquals(result,recipes);
    }

    @Test
    void shouldSaveRecipe() {
        when(recipesRepository.save(recipes)).thenReturn(recipes);

        Recipes result = recipesService.saveRecipe(recipes);

        verify(recipesRepository).save(recipes);
        assertEquals(result,recipes);
    }

    @Test
    void shouldRemoveRecipe() {
        doNothing().when(recipesRepository).deleteById(recipes.getId());

        recipesService.removeRecipe(recipes.getId());

        verify(recipesRepository).deleteById(recipes.getId());
    }
}