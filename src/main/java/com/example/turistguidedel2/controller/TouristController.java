package com.example.turistguidedel2.controller;


import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.service.TouristService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class TouristController {

    private final TouristService touristService;


    public TouristController(TouristService touristService) {
        this.touristService = touristService;
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
/*@GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
    model.addAttribute("attraction",new TouristAttraction());
    return "addAttraction";
}

 */
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

    @GetMapping("/attractions/{name}/edit")
    public String showEditAttractionForm(@PathVariable String name, Model model) {
        Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
        if (attractionOptional.isPresent()) {
            model.addAttribute("attraction", attractionOptional.get());
            return "editAttraction";
        } else {
            return "error";
        }
    }

    @PostMapping("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/attractions";
    }


    @GetMapping("/attractions/add")

    public String showAddAttractionForm(Model model) {

        TouristAttraction newAttraction = new TouristAttraction("", "", "", List.of(""));

        List<String> cities = Arrays.asList("Copenhagen", "Odense", "Aarhus");
        List<String> tags = Arrays.asList("Børnevenligt", "Hyggeligt", "Dejligt");


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

    /* @DeleteMapping("/attractions/{name}")
    public String deleteAttractionByName(@PathVariable String name) {
        touristService.deleteAttractionByName(name);
        return "Attraction with name " + name + "deleted successfully. ";
     }

     */

  /* @GetMapping("/attractions/{name}/delete")
    public String deleteAttraaction(@PathVariable String name) {
        touristService.deleteAttractionByName(name);
        return "redirect:/attractions";
    }


   */

}