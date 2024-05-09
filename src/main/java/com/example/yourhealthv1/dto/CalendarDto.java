package com.example.yourhealthv1.dto;


import java.util.Date;

public class CalendarDto {

    private String name;

    private Date date;

    private String text;

    public CalendarDto() {
    }

    public CalendarDto(String name, Date date, String text) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
