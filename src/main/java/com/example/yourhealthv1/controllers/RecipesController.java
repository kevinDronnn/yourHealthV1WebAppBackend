package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.service.RecipesProductsService;
import com.example.yourhealthv1.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {
    @Autowired
    RecipesService recipesService;

    @PostMapping("/recipe")
    ResponseEntity<String> addRecipe(@RequestParam("recipe_name") String name,
                                     @RequestParam("recipe_description") String description,
                                     @RequestParam("recipe_image") MultipartFile file,
                                     @RequestParam("products_name") String[] productNames,
                                     @RequestParam("products_grams") String[] productGrams) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            byte[] imageBytes = file.getBytes();
            Recipes recipes = new Recipes(name, description, imageBytes);

            List<RecipesProducts> productsList = new ArrayList<>();
            for (int i = 0; i < productNames.length; i++) {
                RecipesProducts recipesProducts = new RecipesProducts();
                recipesProducts.setProductName(productNames[i]);
                recipesProducts.setGrams(Integer.parseInt(productGrams[i]));
                recipesProducts.setRecipes(recipes);
                recipesProducts.setRecipeProductId(recipes.getId());
                productsList.add(recipesProducts);
            }
            recipes.setProducts(productsList);
            recipesService.saveRecipe(recipes);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Server error during file upload");
        }
    }

    @GetMapping("/recipe")
    List<Recipes> getAllRecipes() {
        return recipesService.getRecipes();
    }

    @GetMapping("/recipe/{id}")
    public Recipes getRecipeById(@PathVariable int id) {
        return recipesService.getRecipesById(id);
    }
    @DeleteMapping("/recipe/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipesService.removeRecipe(id);
    }
}

