package com.example.yourhealthv1.repository;

import com.example.yourhealthv1.entity.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipesRepository extends JpaRepository<Recipes,Integer> {
    public List<Recipes> getRecipesByAuthorName(String name);
}
