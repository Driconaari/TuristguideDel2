package com.example.turistguidedel2.repository;

import com.example.turistguidedel2.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TouristRepository {
    private List<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Statens Museum for Kunst", "Museum for Kunst", "København", List.of("Kunst", "Museum")),
            new TouristAttraction("Odense Zoo", "Europas bedste zoo", "Odense", List.of("Børnevenlig")),
            new TouristAttraction("Dyrehaven", "Naturpark med skovområder", "Kongens Lyngby", List.of("Natur", "Gratis")),
            new TouristAttraction("Tivoli", "Forlystelsespark i København centrum", "København", List.of("Børnevenlig"))
    ));

public List<TouristAttraction> findAll(){
    return attractions;
}

  /* public List<TouristAttraction> getAllAttractions() {
       return attractions;
   }

   */

   /*public Optional<TouristAttraction> getAttractionByName(String name) {
       return attractions.stream()
               .filter(attraction -> attraction.getName().equals(name))
               .findFirst();
   }

    */

public void delete (String name) {
    attractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
}

    public void save(TouristAttraction attraction) {
    attractions.add(attraction);
    }


    public void add(TouristAttraction attraction){
    attractions.add(attraction);
    }

    public Optional<TouristAttraction> findByName(String name) {
        return attractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
