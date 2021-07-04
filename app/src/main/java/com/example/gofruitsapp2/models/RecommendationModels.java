package com.example.gofruitsapp2.models;

public class RecommendationModels {
    String name, rating, img_url, description;
    int price;

    public RecommendationModels() {
    }

    public RecommendationModels(String name, String rating, String img_url, String description, int price) {
        this.name = name;
        this.rating = rating;
        this.img_url = img_url;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
