/**
 * Clasa reprezentând un obiect muzeu în aplicație.
 */
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

    /**
     * Constructor pentru obiectul Muzeu.
     *
     * @param id          ID-ul muzeului.
     * @param name        Numele muzeului.
     * @param description Descrierea muzeului.
     * @param location    Locația hartii muzeului (daca exista).
     * @param imageName   Numele imaginii asociate muzeului.
     */
    Museum(int id, String name, String description, String location, String imageName)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.image = imageName;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
