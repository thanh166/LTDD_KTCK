package com.example.drinkandcake.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id, name;
    private float price;
    private int image;

    public Product() {
    }

    public Product(String id, String name, float price, int image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public Product(String id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
