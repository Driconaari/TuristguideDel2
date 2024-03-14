package com.example.turistguidedel2.repository;

import com.example.turistguidedel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {

    private static final String URL = "jdbc:mysql://touristguideasger.mysql.database.azure.com";
    private static final String USERNAME = "asger";
    private static final String PASSWORD = "Bob1234avb";

    public List<TouristAttraction> findAllAttractions() {
        List<TouristAttraction> attractions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM repository.touristattraction";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String city = resultSet.getString("city");
                    String tags = resultSet.getString("tags");
                    TouristAttraction attraction = new TouristAttraction (name, description, city, tags, id);
                    attractions.add(attraction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public List<String> getCities() {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT city FROM repository.touristattraction";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String city = resultSet.getString("city");
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        String query = "SELECT DISTINCT tags FROM repository.touristattraction";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String tag = resultSet.getString("tags");
                tags.add(tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }


    public void updateTouristAttraction(TouristAttraction updatedAttraction) {
        String query = "UPDATE repository.touristattraction SET name = ?, description = ?, city = ?, tags = ?, location = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, updatedAttraction.getName());
            preparedStatement.setString(2, updatedAttraction.getDescription());
            preparedStatement.setString(3, updatedAttraction.getCity());
            preparedStatement.setString(4, updatedAttraction.getTags());
            preparedStatement.setString(5, updatedAttraction.getLocation());
            preparedStatement.setInt(6, updatedAttraction.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


/*

    private final List<String> cities;
    private final List<String> tags;

    public TouristRepository(){
        this.cities = Arrays.asList("Copenhagen", "Odense", "Aarhus", "Helsingør",
                "Roskilde", "Aalborg", "Esbjerg", "Vejle",
                "Frederiksberg", "Horsens", "Randers", "Kolding",
                "Viborg", "Herning", "Silkeborg", "Næstved",
                "Greve", "Tårnby", "Hillerød", "Holstebro"
        );
        this.tags =Arrays.asList("Børnevenligt", "Hyggeligt", "Dejligt", "Smukt", "Castle", "Historic", "Royal", "Palace");
    }

    public List<String> getCities() {
        return cities;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<TouristAttraction> getAttractions() {
        return attractions;
    }

    private List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Rosenborg Castle", "Historic castle in Copenhagen", "Copenhagen", List.of("Castle", "Historic")),
            new TouristAttraction("Frederiksborg Castle", "Renaissance castle in Hillerød", "Hillerød", List.of("Castle", "Renaissance")),
            new TouristAttraction("Kronborg Castle", "Renaissance castle in Helsingør", "Helsingør", List.of("Castle", "Renaissance")),
            new TouristAttraction("Amalienborg Palace", "Royal residence in Copenhagen", "Copenhagen", List.of("Palace", "Royal")),
            new TouristAttraction("Christiansborg Palace", "Government building in Copenhagen", "Copenhagen", List.of("Palace", "Government"))
    ));

    public List<TouristAttraction> findAll() {
        return attractions;
    }


    public void save(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public void add(TouristAttraction attraction) {
        attractions.add(attraction);
    }

    public Optional<TouristAttraction> findByName(String name) {
        return attractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void updateTouristAttraction(TouristAttraction updatedAttraction) {
        // Find the attraction in the ArrayList and update its data
        for (int i = 0; i < attractions.size(); i++) {
            TouristAttraction attraction = attractions.get(i);
            if (attraction.getName().equals(updatedAttraction.getName())) {
                attractions.set(i, updatedAttraction);
                break;

            }
        }
    }
    public void deleteAttractionByName(String name) {
        attractions.removeIf(attraction -> attraction.getName().equals(name));
    }
}


     */