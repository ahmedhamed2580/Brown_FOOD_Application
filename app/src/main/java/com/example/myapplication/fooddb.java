package com.example.myapplication;

public class fooddb {
    int img;
    Double price;
    String name;
    int cardnumber;

    public fooddb(int cardnumber, int img, Double price, String name) {
        this.cardnumber = cardnumber;
        this.img = img;
        this.price = price;
        this.name = name;
    }

    public fooddb(int img, Double price, String name) {
        this.img = img;
        this.price = price;
        this.name = name;
    }


    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
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
