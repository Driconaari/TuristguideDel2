package com.example.turistguidedel2.controller;

import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.repository.TouristRepository;
import com.example.turistguidedel2.service.TouristService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TouristControllerTest {

    @Mock
    private TouristService touristService;

    @Mock
    private TouristRepository touristRepository;

    @InjectMocks
    private TouristController touristController;

    @Test
    public void testShowHomePage() {
        // Given
        List<TouristAttraction> attractions = new ArrayList<>();
        when(touristService.getAllAttractions()).thenReturn(attractions);
        Model model = mock(Model.class);

        // When
        String viewName = touristController.showHomePage(model);

        // Then
        assertThat(viewName).isEqualTo("index");
        verify(model).addAttribute(eq("attractions"), anyList());
    }
}
