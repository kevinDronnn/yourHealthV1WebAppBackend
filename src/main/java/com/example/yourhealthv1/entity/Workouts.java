package com.example.yourhealthv1.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "health_workouts")
public class Workouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "date")
    private Date date;
    @Column(name = "duration")
    private int duration;


    public Workouts() {
    }

    public Workouts(String name, Date date, int duration) {
        this.name = name;
        this.date = date;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Workouts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
