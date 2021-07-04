package com.example.gofruitsapp2.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String description, img_url, name, rating, type;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String description, String img_url, String name, String rating, String type, int price) {
        this.description = description;
        this.img_url = img_url;
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
