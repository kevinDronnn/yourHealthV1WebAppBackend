package com.example.yourhealthv1.dto;



public class CommentDto {
    private String text;

    private String authorName;

    private int recipes;

    public CommentDto() {
    }

    public CommentDto(String text, String authorName, int recipes) {
        this.text = text;
        this.authorName = authorName;
        this.recipes = recipes;
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
}
