package com.example.turistguidedel2.controller;
/*
import com.example.turistguidedel2.model.TouristAttraction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.turistguidedel2.service.TouristService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristService touristService;

    @InjectMocks
    TouristController touristController;


    @Test
    public void testDeleteAttraction() throws Exception {
        String attractionName = "AttractionName";

        mockMvc.perform(MockMvcRequestBuilders.delete("/attractions/{name}/delete", attractionName))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        verify(touristService).deleteAttractionByName(attractionName);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllAttractions() throws Exception{
    }

    @Test
    void getAttractionTags() {
    }

    @Test
    void showAttractions() {
    }

    @Test
    void saveAttraction() {
    }

    @Test
    void showAddAttractionForm() {
    }
    @Test
    void deleteAttraction() {
    }

    @Test
    void showEditAttractionFormByName() {
    }
    @Test
    void updateAttraction() {
    }
}

 */