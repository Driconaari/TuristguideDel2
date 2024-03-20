package com.example.turistguidedel2.model;

import java.util.Arrays;
import java.util.Collections;

public class TouristAttraction {


    private String name;
    private String description;
    private String city;
    private String tags;
    private int id;

    private String location;

    public TouristAttraction(int id, String name, String description, String tags, String location, String city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.location = location;
        this.city = city;
    }



    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

   /* public void setTags(String tags) {
        this.tags = tags;
    }

    */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getTags() {
        return tags;
    }

    public String getLocation() {
        return location;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

