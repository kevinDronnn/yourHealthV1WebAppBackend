package com.example.yourhealthv1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "recipe_id")
    private int recipes;

    public Comments() {
    }

    public Comments(String text, String authorName, int recipes) {
        this.text = text;
        this.authorName = authorName;
        this.recipes = recipes;
    }

    public Comments(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getRecipes() {
        return recipes;
    }

    public void setRecipes(int recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", authorName='" + authorName + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
