package com.example.yourhealthv1.dto;


import java.time.LocalDate;
import java.util.Date;

public class CalendarDto {

    private String name;

    private LocalDate date;

    private String text;

    public CalendarDto() {
    }

    public CalendarDto(String name, LocalDate date, String text) {
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CalendarDto{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
