package com.example.yourhealthv1.service;

import com.example.yourhealthv1.controller.AdvicesController;
import com.example.yourhealthv1.entity.Recipes;
import com.example.yourhealthv1.repository.RecipesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RecipesService {

    private static Logger logger = LoggerFactory.getLogger(AdvicesController.class);
    @Autowired
    private RecipesRepository recipesRepository;

    public List<Recipes> getRecipes() {
        return recipesRepository.findAll();
    }

    public List<Recipes> getRecipesByAuthor(String name) {
        return recipesRepository.getRecipesByAuthorName(name);
    }

    public Recipes getRecipesById(int id) {
        Optional<Recipes> recipes = Optional.ofNullable(recipesRepository.getReferenceById(id));
        if (recipes.isPresent()){
            return recipes.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public Recipes saveRecipe(Recipes recipe) {
        logger.info("Recipe was added: " + recipe.getName());
        return recipesRepository.save(recipe);
    }

    @Transactional
    public void removeRecipe(int id) {
        recipesRepository.deleteById(id);
        logger.info("Recipe with id="+id+" was deleted");
    }
}

