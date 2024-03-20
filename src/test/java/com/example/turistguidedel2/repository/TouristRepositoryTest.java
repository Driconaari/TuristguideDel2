package com.example.turistguidedel2.repository;

import com.example.turistguidedel2.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TouristRepositoryTest {


    private TouristRepository repository;
    @BeforeEach
    void setUp() {
        repository = new TouristRepository();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindAll() {
        List<TouristAttraction> attractions = repository.findAll();
        assertEquals(5, attractions.size());
    }

   /* @Test
    void testSave() {
        TouristAttraction newAttraction = new TouristAttraction("", "Description", "Location", List.of("Tag1", "Tag2"));
        repository.save(newAttraction);

        List<TouristAttraction> attractions = repository.findAll();
        assertTrue(attractions.contains(newAttraction));
    }

    */

    @Test
    void add() {
    }

    @Test
    void testFindByName() {
        Optional<TouristAttraction> attraction = repository.findByName("Rosenborg Castle");
        assertTrue(attraction.isPresent());
        assertEquals("Rosenborg Castle", attraction.get().getName());
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void deleteAttractionByName() {
    }
}