package com.example.yourhealthv1.service;

import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.repository.RecipesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesService {

    @Autowired
    private RecipesRepository recipesRepository;

    public List<Recipes> getRecipes() {
        return recipesRepository.findAll();
    }

    public Recipes getRecipesById(int id) {
        return recipesRepository.getReferenceById(id);
    }

    @Transactional
    public Recipes saveRecipe(Recipes recipe) {
        return recipesRepository.save(recipe);
    }

    @Transactional
    public void removeRecipe(int id) {
        recipesRepository.deleteById(id);
    }
}

