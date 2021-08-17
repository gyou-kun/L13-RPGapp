package sg.edu.rp.c346.id20024466.l13_rpgapp;

import java.io.Serializable;

public class Weapon implements Serializable {

    private int id;
    private String name;
    private String description;
    private int price;
    private int stars;

    public Weapon(String name, String description, int price, int stars) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stars = stars;
    }

    public Weapon(int id, String name, String description, int square, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = square;
        this.stars = stars;
    }

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < price; i++) {
            starsString += " * ";
        }
        return starsString;

    }


    }
