package com.example.turistguidedel2.controller;


import ch.qos.logback.core.model.Model;
import com.example.turistguidedel2.model.TouristAttraction;
import com.example.turistguidedel2.service.TouristService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class TouristController {
    
private final TouristService touristService;


public  TouristController(TouristService touristService) {
    this.touristService = touristService;
}

@GetMapping ("/attractions")

    public String getAllAttraction(Model model) {
    List<TouristAttraction> attractions = touristService.getAllAttractions();
    model.addAttribute("attractions", attractions);
    return "attractionList";
}

@GetMapping("/attractions/{name}/tags")
    public String getAttractionTags(@PathVariable String name, Model model){
    Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
    if(attractionOptional.isPresent()) {
        TouristAttraction attraction = attractionOptional.get();
        model.addAttribute("attraction", attraction);
        return "tags";
    }else {
        //if attraction isnt found
        return "error";
    }
}
@GetMapping("/attractions/add")
    public String showAddAttractionForm(Model model) {
    model.addAttribute("attraction",new TouristAttraction());
    return "addAttraction";
}

@PostMapping("attractions/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
    touristService.saveAttraction(attraction);
    return  "redirect:/attractions";
}

@GetMapping("/attractions/{name}/edit")
    public String showEditAttractionForm(@PathVariable String name, Model model) {
    Optional<TouristAttraction> attractionOptional = touristService.getAttractionByName(name);
    if (attractionOptional.isPresent()){
        model.addAttribute("attraction", attractionOptional.get());
        return "editAttraction";
    }else {
        return "error";
    }
}
@PostMapping ("/attractions/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
    touristService.updateAttraction(attraction);
    return "redirect:/attractions";
}

@GetMapping("/attractions/{name}/delete")
    public String deleteAttraaction(@PathVariable String name) {
    touristService.deletAttractionByName(name);
    return "redirect:/attractions";
}

}