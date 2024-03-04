package com.example.turistguidedel2.repository;

import com.example.turistguidedel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {


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
