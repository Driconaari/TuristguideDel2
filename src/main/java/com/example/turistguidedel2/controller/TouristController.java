package com.example.turistguidedel2.controller;


import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.repository.TouristRepository;
import com.example.turistguidedel2.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TouristController {

    private final TouristService touristService;

    private TouristRepository touristRepository;

    @Autowired
    public TouristController(TouristService touristService, TouristRepository touristRepository) {
        this.touristService = touristService;
        this.touristRepository = touristRepository;
    }

    public TouristController(TouristService touristService, List<String> cities) {
        this.touristService = touristService;
    }

    @GetMapping("/attractions/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            TouristAttraction attraction = attractionOptional.get();
            model.addAttribute("attraction", attraction);
            return "tags";
        } else {
            //if attraction isn't found
            return "error";
        }
    }

    //show attractions, I saved the old version
    @GetMapping("/")
    public String showHomePage(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "index"; // Assuming "index" is the name of your Thymeleaf template for the homepage
    }

    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList"; // Assuming "attractionList" is the name of your Thymeleaf template
    }

    @PostMapping("/attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.saveAttraction(attraction);
        return "redirect:/";
    }

    //new add attraction
    @GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
        // Create a new empty attraction
        TouristAttraction newAttraction = new TouristAttraction();
        newAttraction.setName("Name");
        newAttraction.setDescription("Description");
        newAttraction.setCity("City");
        newAttraction.setTags("Tags");
        newAttraction.setLocation("Location");

        // Fetch cities from the database
        List<String> cities = touristRepository.getCities();

        // Fetch all tags from the database using the getAllTags method
        List<String> tags = touristRepository.getAllTags(); // Ensure this method retrieves all tags correctly

        // Add the attributes to the model
        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cities", cities);
        model.addAttribute("tags", tags);

        return "addAttraction";
    }


    @PostMapping("/attractions/add")
    public String addAttraction(@ModelAttribute("attraction") TouristAttraction attraction) {
        // Assuming you have setters in TouristAttraction for all fields
        // Set other fields as needed before saving
        attraction.setName("Name");
        attraction.setDescription("Description");
        attraction.setCity("City");
        attraction.setTags("Tags");
        attraction.setLocation("Location");

        // Now you can save this new attraction using the service layer
        touristService.saveAttraction(attraction);

        // Redirect to a success page or another appropriate view
        return "redirect:/attractions";
    }

    @GetMapping("/attractions/edit/{name}")
    public String showEditAttractionFormByName(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            TouristAttraction attraction = attractionOptional.get();
            model.addAttribute("attraction", attraction);

            // Fetch cities from the repository
            List<String> cities = touristRepository.getCities();

            // Get the ID of the attraction
            int attractionId = attraction.getId();

            // Fetch tags for the specific attraction by its ID
            List<String> tags = touristRepository.getTags(attraction.getId());


            model.addAttribute("cities", cities);
            model.addAttribute("tags", tags);

            return "editAttraction"; // Assuming "editAttraction" is the name of your Thymeleaf template for editing an attraction
        } else {
            // Handle attraction not found
            return "error";
        }
    }


    @DeleteMapping("/attractions/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttractionByName(name);
        //return "Attraction with name " + name + " deleted successfully.";
        return "redirect:/";
    }


    @GetMapping("/cities")
    public String showCities(Model model) {
        List<String> cities = touristRepository.getCities();
        model.addAttribute("cities", cities);
        return "citiesView";
    }


    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction updatedAttraction) {
        touristRepository.updateTouristAttraction(updatedAttraction);
        return "redirect:/attractions"; // Redirect to the attraction list page after updating
    }


}