package com.example.yourhealthv1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "health_recipes")
public class Recipes {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "recipes")
    @JsonManagedReference
    private List<RecipesProducts> products;

    @Column(name = "author_name")
    private String authorName;


    @Column(name = "total_grams")
    private double grams;
    @Column(name = "cals")
    private double cals;
    @Column(name = "proteins")
    private double proteins;
    @Column(name = "carbs")
    private double carbs;
    @Column(name = "fats")
    private double fats;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private List<Comments> commentsList;



    public Recipes() {
    }


    public Recipes(String name, String description, String image, String authorName, double grams, double cals, double proteins, double carbs, double fats) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.authorName = authorName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImageString (String image) {
        this.image = image;
    }

    public List<RecipesProducts> getProducts() {
        return products;
    }

    public void setProducts(List<RecipesProducts> products) {
        this.products = products;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }


    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }

    public double getCals() {
        return cals;
    }

    public void setCals(double cals) {
        this.cals = cals;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
