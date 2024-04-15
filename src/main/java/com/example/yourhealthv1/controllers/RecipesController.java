package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.service.RecipesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    RecipesService recipesService;

    @PostMapping("/recipe")
    ResponseEntity<String> addRecipe(@RequestParam("recipe_name") String name,
                                     @RequestParam("recipe_description") String description,
                                     @RequestParam("authorName") String authorName,
                                     @RequestParam("recipe_image") MultipartFile file,
                                     @RequestParam("products_name") String[] productNames,
                                     @RequestParam("products_grams") String[] productGrams) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
//            byte[] imageBytes = file.getBytes();
            String path = file.getOriginalFilename();
            File file2 = new File
                    ("C:\\Users\\voros\\Desktop\\Your health\\images\\Recipces Page\\"
                            + name + path.substring(path.lastIndexOf(".")));
            file.transferTo(file2);
//            Recipes recipes = new Recipes(name, description, imageBytes);
            Recipes recipes = new Recipes(name, description, file2.getAbsolutePath(), authorName);

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
            logger.info("Recipe was added: " + recipes.getName());
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

    @GetMapping("/recipe/author/{author}")
    List<Recipes> getAllRecipesOfAuthor(@PathVariable String author) {
        return recipesService.getRecipesByAuthor(author);
    }

    @GetMapping("/recipe/{id}")
    public Recipes getRecipeById(@PathVariable int id) {
        return recipesService.getRecipesById(id);
    }

    @DeleteMapping("/recipe/delete/{id}")
    public void deleteRecipe(@PathVariable int id) {
        logger.info("Recipe with id="+id+" was deleted");
        recipesService.removeRecipe(id);
    }
}

