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
//    @Lob
//    @Column(name = "image", columnDefinition = "LONGBLOB")
//    private byte[] image;

    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "recipes")
    @JsonManagedReference
    private List<RecipesProducts> products;


    public Recipes() {
    }

    public Recipes(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
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

    @Override
    public String toString() {
        return "Recipes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" +  image +
//                ", products=" + products +
                '}';
    }
}
