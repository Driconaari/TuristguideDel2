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

import static org.yaml.snakeyaml.tokens.Token.ID.Tag;

@Controller
public class TouristController {

    private final TouristService touristService;

    @Autowired
    private TouristRepository touristRepository;

    private final List<String> cities;
    private final List<String> tags;


    public TouristController(TouristService touristService, List<String> cities) {
        this.touristService = touristService;
        this.cities = Arrays.asList("Copenhagen", "Odense", "Aarhus", "Helsingør",
                "Roskilde", "Aalborg", "Esbjerg", "Vejle",
                "Frederiksberg", "Horsens", "Randers", "Kolding",
                "Viborg", "Herning", "Silkeborg", "Næstved",
                "Greve", "Tårnby", "Hillerød", "Holstebro"
        );
        this.tags =Arrays.asList("Børnevenligt", "Hyggeligt", "Dejligt", "Smukt", "Castle", "Historic", "Royal", "Palace");

    }
   /* @GetMapping("/attractions")
    public String getAllAttraction(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    */
    @GetMapping("/attractions")
public String getAllAttractions(Model model) {
    List<TouristAttraction> attractions = touristService.getAllAttractions();
    model.addAttribute("attractions", attractions);
    return "attractionList"; // Assuming "attractionList" is the name of your Thymeleaf template
}

    @GetMapping("/attractions/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            TouristAttraction attraction = attractionOptional.get();
            model.addAttribute("attraction", attraction);
            return "tags";
        } else {
            //if attraction isnt found
            return "error";
        }
    }

    @GetMapping("/")
    public String showAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "index";
    }

    /*@GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
        // Create a new instance of TouristAttraction with default values
        TouristAttraction newAttraction = new TouristAttraction("", "", "", List.of(""));
        model.addAttribute("attraction", newAttraction);
        return "addAttraction";
    }

     */

    @PostMapping("attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.saveAttraction(attraction);
        return  "redirect:/";
       // return "redirect:/attractions";
    }

/*
    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions";
    }

 */


    @GetMapping("/attractions/add")

    public String showAddAttractionForm(Model model) {

        TouristAttraction newAttraction = new TouristAttraction("", "", "", List.of(""));

        List<String> cities = Arrays.asList(
                "Copenhagen", "Odense", "Aarhus", "Helsingør",
                "Roskilde", "Aalborg", "Esbjerg", "Vejle",
                "Frederiksberg", "Horsens", "Randers", "Kolding",
                "Viborg", "Herning", "Silkeborg", "Næstved",
                "Greve", "Tårnby", "Hillerød", "Holstebro"
        );        List<String> tags = Arrays.asList("Børnevenligt", "Hyggeligt", "Dejligt", "Smukt", "Castle", "Historic", "Royal", "Palace");


        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cities", cities);
        model.addAttribute("tags", tags);

        return "addAttraction";
    }
//Delete methods


    @DeleteMapping("/attractions/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttractionByName(name);
        //return "Attraction with name " + name + " deleted successfully.";
        return "redirect:/";
    }


    /*
        @GetMapping("/attractions/{name}/edit")
    public String showEditAttractionForm(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            model.addAttribute("attraction", attractionOptional.get());
            return "editAttraction"; // Assuming "editAttraction" is the name of your Thymeleaf template for editing an attraction
        } else {
            // Handle attraction not found
            return "error";
        }
    }

     */

   /* @GetMapping("/attractions/edit/{name}")
    public String showEditAttractionFormByName(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            model.addAttribute("attraction", attractionOptional.get());
            model.addAttribute("cities", cities); // Use the class-level cities list
            model.addAttribute("tags", Tag.values());
            return "editAttraction"; // Assuming "editAttraction" is the name of your Thymeleaf template for editing an attraction
        } else {
            // Handle attraction not found
            return "error";
        }
    }

    */

    @GetMapping("/attractions/edit/{name}")
    public String showEditAttractionFormByName(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            TouristAttraction attraction = attractionOptional.get();
            model.addAttribute("attraction", attraction);
            model.addAttribute("cities", cities); // Use the class-level cities list
            model.addAttribute("tags", tags); // Add the tags list to the model
            return "editAttraction"; // Assuming "editAttraction" is the name of your Thymeleaf template for editing an attraction
        } else {
            // Handle attraction not found
            return "error";
        }
    }

   /* @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions"; // Redirect to the attraction list page after updating
    }

    */

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction updatedAttraction) {
        touristRepository.updateTouristAttraction(updatedAttraction);
        return "redirect:/attractions"; // Redirect to the attraction list page after updating
    }
}