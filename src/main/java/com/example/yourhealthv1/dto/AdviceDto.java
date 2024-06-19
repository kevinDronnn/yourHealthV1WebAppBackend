package com.example.yourhealthv1.dto;

public class AdviceDto {
    private int id;
    private String title;

    private String description;

    private String authorName;

    public AdviceDto() {
    }

    public AdviceDto(int id, String title, String description, String authorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorName = authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
