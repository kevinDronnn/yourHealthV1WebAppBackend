package com.example.yourhealthv1.dto;

import java.util.Date;

public class WeightDto {

    private String name;

    private Date date;

    private float weight;

    public WeightDto() {
    }

    public WeightDto(String name, Date date, float weight) {
        this.name = name;
        this.date = date;
        this.weight = weight;
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
}
