package com.example.myapplication;

public class loveclass {
    int img;
    Double price;
    String name;
    int cardnumber;

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

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public loveclass(int img, Double price, String name, int cardnumber) {
        this.img = img;
        this.price = price;
        this.name = name;
        this.cardnumber = cardnumber;
    }

    public loveclass(int img, Double price, String name) {
        this.img = img;
        this.price = price;
        this.name = name;

    }
}
