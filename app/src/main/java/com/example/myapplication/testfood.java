package com.example.myapplication;

public class testfood {
    int img;
    Double price;
    String name;

    public testfood(int img, Double price, String name) {
        this.img = img;
        this.price = price;
        this.name = name;
    }

    public testfood(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
