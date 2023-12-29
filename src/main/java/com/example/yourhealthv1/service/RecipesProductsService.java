package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.RecipesProducts;
import com.example.yourhealthv1.repository.RecipesProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesProductsService {

    @Autowired
    private RecipesProductsRepository recipesProductsRepository;

    public List<RecipesProducts> getAllRecipesProducts() {
        return recipesProductsRepository.findAll();
    }

    public RecipesProducts getRecipeProductById(int id) {
        return recipesProductsRepository.getReferenceById(id);
    }

    @Transactional
    public RecipesProducts saveRecipeProduct(RecipesProducts recipeProduct) {
        return recipesProductsRepository.save(recipeProduct);
    }

    @Transactional
    public void deleteRecipeProduct(int id) {
        recipesProductsRepository.deleteById(id);
    }
}