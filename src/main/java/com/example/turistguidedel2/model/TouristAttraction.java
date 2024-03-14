package com.example.turistguidedel2.model;

public class TouristAttraction {


    private String name;
    private String description;
    private String city;
    private String tags;
    private int id;

    private String location;

    public TouristAttraction(String name, String description, String city, String tags, int id, String location) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
        this.id = id;
        this.location = location;
    }


    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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
}

