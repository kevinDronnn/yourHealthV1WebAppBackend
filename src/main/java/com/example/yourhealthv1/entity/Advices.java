package com.example.yourhealthv1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_advices")
public class Advices {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    public Advices() {
    }

    public Advices(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Advices{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
