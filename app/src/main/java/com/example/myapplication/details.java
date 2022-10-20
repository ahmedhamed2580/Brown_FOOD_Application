package com.example.myapplication;


public class details {
    String name;
    String description;
    int pic;
    Double fes;
    int id;


    public details(String name, String description, int pic, Double fes) {
        this.name = name;
        this.description = description;
        this.pic = pic;
        this.fes = fes;
    }

    public details(String name, String description, int pic, Double fes, int id) {
        this.name = name;
        this.description = description;
        this.pic = pic;
        this.fes = fes;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getFes() {
        return fes;
    }

    public void setFes(Double fes) {
        this.fes = fes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}

