package com.example.yourhealthv1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_products")
public class Products {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "grams")
    private float grams;
    @Column(name = "cals")
    private float cals;
    @Column(name = "proteins")
    private float proteins;
    @Column(name = "carbs")
    private float carbs;
    @Column(name = "fats")
    private float fats;



    public Products() {
    }

    public Products(String name, float grams, float cals, float proteins, float carbs, float fats) {
        this.name = name;
        this.grams = grams;
        this.cals = cals;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", grams='" + grams + '\'' +
                ", cals='" + cals + '\'' +
                ", proteins='" + proteins + '\'' +
                ", carbs='" + carbs + '\'' +
                ", fats='" + fats + '\'' +
                '}';
    }
}
