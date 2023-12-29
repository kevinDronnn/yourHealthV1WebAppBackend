package com.example.yourhealthv1.controllers;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.service.RecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RecipesController {
        @Autowired
        RecipesService recipesService;

        @PostMapping("/recipe")
        ResponseEntity<String> addRecipe(@RequestParam("name") String name,
                                         @RequestParam("description") String description,
                                         @RequestParam("image") MultipartFile file){
            try {

                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().body("File is empty");
                }

                byte[] imageBytes = file.getBytes();

                Recipes recipes = new Recipes(name, description, imageBytes);
                recipesService.saveRecipe(recipes);

                return ResponseEntity.ok("File uploaded successfully");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Server error during file upload");
            }
        }

        @GetMapping("/recipe")
        List<Recipes> getAllRecipes(){
            return recipesService.getRecipes();
        }

        @DeleteMapping("/recipe/{id}")
        public void deleteRecipe(@PathVariable int id) {
            recipesService.removeRecipe(id);
        }
}
