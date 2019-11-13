package com.example.game4sell.Model;

public class Products {

    public String Title, Category, Description;
    public int Thumbnail;
    public Double Price;

    public Products(String title, String category, String description, Integer thumbnail, Double price) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = thumbnail;
        Price = price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(Integer thumbnail) {
        Thumbnail = thumbnail;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
