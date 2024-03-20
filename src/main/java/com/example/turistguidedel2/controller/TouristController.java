package com.example.turistguidedel2.controller;


import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.repository.TouristRepository;
import com.example.turistguidedel2.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
//show attractions, I save the old version
@GetMapping("/")
public String showHomePage(Model model) {
    List<TouristAttraction> attractions = touristService.getAllAttractions();
    model.addAttribute("attractions", attractions);
    return "index"; // Assuming "index" is the name of your Thymeleaf template for the homepage
}
    @GetMapping("/attractions/all")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList"; // Assuming "attractionList" is the name of your Thymeleaf template
    }
    /*
//old method
    @GetMapping("/attractions")
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList"; // Assuming "attractionList" is the name of your Thymeleaf template
    }

 */
/*
    @GetMapping("/")
    public String showAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "index";
    }

 */


    @PostMapping("attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.saveAttraction(attraction);
        return "redirect:/";
        // return "redirect:/attractions";
    }


    @GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
        TouristAttraction newAttraction = new TouristAttraction(0, "", "", "", "", "");

        // Fetch cities from the database
        List<String> cities = touristRepository.getCities(); // Assuming you have a method in your repository to fetch cities

        // Fetch tags from the database
        List<String> tags = touristRepository.getTags(); // Assuming you have a method in your repository to fetch tags

        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cities", cities);
        model.addAttribute("tags", tags);

        return "addAttraction";
    }

/*
    @GetMapping("/attractions/add")

    public String showAddAttractionForm(Model model) {

        TouristAttraction newAttraction = new TouristAttraction("", "", "", List.of(""));

        List<String> cities = Arrays.asList(
                "Copenhagen", "Odense", "Aarhus", "Helsingør",
                "Roskilde", "Aalborg", "Esbjerg", "Vejle",
                "Frederiksberg", "Horsens", "Randers", "Kolding",
                "Viborg", "Herning", "Silkeborg", "Næstved",
                "Greve", "Tårnby", "Hillerød", "Holstebro"
        );
        List<String> tags = Arrays.asList("Børnevenligt", "Hyggeligt", "Dejligt", "Smukt", "Castle", "Historic", "Royal", "Palace");


        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cities", cities);
        model.addAttribute("tags", tags);

        return "addAttraction";
    }

 */
//Delete methods


    @DeleteMapping("/attractions/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttractionByName(name);
        //return "Attraction with name " + name + " deleted successfully.";
        return "redirect:/";
    }

// mappings for hardcoded arraylist
    @GetMapping("/attractions/edit/{name}")
    public String showEditAttractionFormByName(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            TouristAttraction attraction = attractionOptional.get();
            model.addAttribute("attraction", attraction);

            // Use the lists from the repository class
            List<String> cities = touristRepository.getCities();
            List<String> tags = touristRepository.getTags();

            model.addAttribute("cities", cities);
            model.addAttribute("tags", tags);

            return "editAttraction"; // Assuming "editAttraction" is the name of your Thymeleaf template for editing an attraction
        } else {
            // Handle attraction not found
            return "error";
        }
    }

    @GetMapping("/cities")
    public String showCities(Model model) {
        List<String> cities = touristRepository.getCities();
        model.addAttribute("cities", cities);
        return "citiesView";
    }

    @GetMapping("/tags")
    public String showTags(Model model) {
        List<String> tags = touristRepository.getTags();
        model.addAttribute("tags", tags);
        return "tagsView";
    }

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction updatedAttraction) {
        touristRepository.updateTouristAttraction(updatedAttraction);
        return "redirect:/attractions"; // Redirect to the attraction list page after updating
    }

}