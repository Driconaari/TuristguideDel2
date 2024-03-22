package com.example.turistguidedel2.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.repository.TouristRepository;

public class TouristRepositoryTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private TouristRepository touristRepository;
    private TouristRepository repository;
    @BeforeEach
    void setUp() {
        repository = new TouristRepository();
    }

    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristRepository = new TouristRepository();
    }

    @Test
    public void testFindAll() throws Exception {
        // Mocking database interactions
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Stubbing ResultSet behavior
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("name")).thenReturn("Attraction 1", "Attraction 2");
        when(resultSet.getString("description")).thenReturn("Description 1", "Description 2");
        when(resultSet.getString("tags")).thenReturn("Tag1,Tag2", "Tag3,Tag4");
        when(resultSet.getString("location")).thenReturn("Location 1", "Location 2");
        when(resultSet.getString("city")).thenReturn("City 1", "City 2");

        // Call the method under test
        List<TouristAttraction> attractions = touristRepository.findAll();

        // Verify the results
        // Add your assertions here
    }
    @Test
    void testFindByName() {
        Optional<TouristAttraction> attraction = repository.findByName("Rosenborg Castle");
        assertTrue(attraction.isPresent());
        assertEquals("Rosenborg Castle", attraction.get().getName());
    }
}

