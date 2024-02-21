package com.example.turistguidedel2.service;

import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    @Autowired
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.findAll();
    }

    public Optional<TouristAttraction> getAttractionByName(String name) {
        return touristRepository.findByName(name);
    }

    public void saveAttraction(TouristAttraction attraction) {
        touristRepository.save(attraction);
    }

/*public void updateAttraction(TouristAttraction attraction) {
        touristRepository.save(attraction);
    }
 */
public void updateAttraction(TouristAttraction updatedAttraction) {
    Optional<TouristAttraction> existingAttractionOptional = touristRepository.findByName(updatedAttraction.getName());
    if (existingAttractionOptional.isPresent()) {
        TouristAttraction existingAttraction = existingAttractionOptional.get();
        // Update the existing attraction with new information
        existingAttraction.setDescription(updatedAttraction.getDescription());
        existingAttraction.setCity(updatedAttraction.getCity());
        existingAttraction.setTags(updatedAttraction.getTags());
        // Save the updated attraction
        touristRepository.save(existingAttraction);
    } else {
        // Handle the case where the attraction to be updated doesn't exist
        // This could involve throwing an exception or logging an error
    }
}

    public void addTouristAttraction(TouristAttraction attraction) {
        touristRepository.save(attraction);
    }

    public void deleteAttractionByName(String name) {
        touristRepository.deleteAttractionByName(name);
    }
}
