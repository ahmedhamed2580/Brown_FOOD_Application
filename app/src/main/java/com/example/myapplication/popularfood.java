package com.example.myapplication;

public class popularfood {
    int image1,image2;
    String name,subname;
    Double fee;
    String color;

    public popularfood(int image1, int image2, String name, String subname, Double fee, String color) {
        this.image1 = image1;
        this.image2 = image2;
        this.name = name;
        this.subname = subname;
        this.fee = fee;
        this.color = color;
    }

    public popularfood(int image1, int image2, String name, String subname, Double fee) {
        this.image1 = image1;
        this.image2 = image2;
        this.name = name;
        this.subname = subname;
        this.fee = fee;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
