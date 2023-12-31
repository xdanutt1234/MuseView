package com.myapp.museview;

public class Marker {
    private int id;
    private String name;
    private String description;
    private float x;
    private float y;
    private int museum;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMuseum() {
        return museum;
    }

    public void setMuseum(int museum) {
        this.museum = museum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Marker(int id, String name, String description, float x, float y, int museum, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.museum = museum;
        this.image = image;
    }
}
