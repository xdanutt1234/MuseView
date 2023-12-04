package com.myapp.museview;

public class Museum {

    private int id;
    private String name;
    private String description;
    private String location;
    private String image;

    public String getImage() {
        return image;
    }

    public int getId() {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }
    public String getLocation() { return this.location; }

    Museum(int id, String name, String description, String location, String imageName)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.image = imageName;
    }

}
