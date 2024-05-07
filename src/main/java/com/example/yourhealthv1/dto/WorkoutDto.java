package com.example.yourhealthv1.dto;

import java.util.Date;

public class WorkoutDto {
    private String name;
    private Date date;
    private int duration;

    public WorkoutDto() {
    }

    public WorkoutDto(String name, Date date, int duration) {
        this.name = name;
        this.date = date;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
