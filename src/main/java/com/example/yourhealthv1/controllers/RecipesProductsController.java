package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.service.RecipesProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesProductsController {
    @Autowired
    RecipesProductsService recipesProductsService;

    @PostMapping("/recipesProduct")
    RecipesProducts addRecipesProducts(@RequestBody RecipesProducts newRecipesProducts) {
        return recipesProductsService.saveRecipeProduct(newRecipesProducts);
    }

    @GetMapping("/recipesProduct")
    List<RecipesProducts> getAllRecipesProducts() {
        return recipesProductsService.getAllRecipesProducts();
    }

    @DeleteMapping("/recipesProduct/{id}")
    public void deleteRecipeProducts(@PathVariable int id) {
        recipesProductsService.deleteRecipeProduct(id);
    }
}
