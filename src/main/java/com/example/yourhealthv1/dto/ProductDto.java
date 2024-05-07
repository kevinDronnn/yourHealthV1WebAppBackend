package com.example.yourhealthv1.dto;

public class ProductDto {

    private String name;

    private float grams;

    private float cals;

    private float proteins;

    private float carbs;

    private float fats;

    public ProductDto() {
    }

    public ProductDto(String name, float grams, float cals, float proteins, float carbs, float fats) {
        this.name = name;
        this.grams = grams;
        this.cals = cals;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getGrams() {
        return grams;
    }

    public void setGrams(float grams) {
        this.grams = grams;
    }

    public float getCals() {
        return cals;
    }

    public void setCals(float cals) {
        this.cals = cals;
    }

    public float getProteins() {
        return proteins;
    }

    public void setProteins(float proteins) {
        this.proteins = proteins;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public float getFats() {
        return fats;
    }

    public void setFats(float fats) {
        this.fats = fats;
    }
}
