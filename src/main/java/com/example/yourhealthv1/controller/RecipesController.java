package com.example.yourhealthv1.controller;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {

    @Autowired
    RecipesService recipesService;

    @PostMapping("/recipe")
    ResponseEntity<String> addRecipe(@RequestParam("recipe_name") String name,
                                     @RequestParam("recipe_description") String description,
                                     @RequestParam("authorName") String authorName,
                                     @RequestParam("recipe_image") MultipartFile file,
                                     @RequestParam("products_name") String[] productNames,
                                     @RequestParam("products_grams") String[] productGrams,
                                     @RequestParam("total_grams") double totalGrams,
                                     @RequestParam("carbs") double carbs,
                                     @RequestParam("cals") double cals,
                                     @RequestParam("fats") double fats,
                                     @RequestParam("proteins") double proteins) {
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
            Recipes recipes = new
                    Recipes(name, description, file2.getAbsolutePath(), authorName, totalGrams , cals , proteins , carbs , fats);

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
    ResponseEntity<List<Recipes>> getAllRecipes() {
        return ResponseEntity.ok(recipesService.getRecipes());
    }

    @GetMapping("/recipe/author/{author}")
    ResponseEntity<List<Recipes>> getAllRecipesOfAuthor(@PathVariable String author) {
        try {
            return ResponseEntity.ok(recipesService.getRecipesByAuthor(author));
        }catch (NoSuchElementException e){
            return ResponseEntity.badRequest().body(List.of(new Recipes()));
        }
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipes> getRecipeById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(recipesService.getRecipesById(id));
        }catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(new Recipes());
        }
    }

    @DeleteMapping("/recipe/delete/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable int id) {
        recipesService.removeRecipe(id);
        return ResponseEntity.ok("Recipe with id="+id+" was deleted");
    }
}

