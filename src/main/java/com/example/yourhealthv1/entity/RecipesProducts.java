package com.example.yourhealthv1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_products")
public class RecipesProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_product_id")
    private int recipeProductId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "grams")
    private int grams;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipes recipes;

    public RecipesProducts() {
    }

    public RecipesProducts(String productName, int grams) {
        this.productName = productName;
        this.grams = grams;
    }

    public int getRecipeProductId() {
        return recipeProductId;
    }

    public void setRecipeProductId(int recipeProductId) {
        this.recipeProductId = recipeProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipes recipe) {
        this.recipes = recipe;
    }

    @Override
    public String toString() {
        return "RecipesProducts{" +
                "recipeProductId=" + recipeProductId +
                ", productName='" + productName + '\'' +
                ", grams=" + grams +
                ", recipe=" + recipes +
                '}';
    }
}
