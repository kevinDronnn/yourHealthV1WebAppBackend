package com.example.yourhealthv1.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "health_weight")
public class Weight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    @Column(name = "weight")
    private float weight;

    public Weight() {
    }

    public Weight(String name, Date date, float weight) {
        this.name = name;
        this.date = date;
        this.weight = weight;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", weight=" + weight +
                '}';
    }
}
