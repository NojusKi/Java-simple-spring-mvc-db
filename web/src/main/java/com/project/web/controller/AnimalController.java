package com.project.web.controller;



import com.project.web.dto.Animaldto;
import com.project.web.models.Animal;
import com.project.web.service.AnimalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AnimalController {
    private AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public String listAnimals(Model model) {
        List<Animaldto> animals = animalService.findAllAnimals();
        model.addAttribute("animals", animals);
        return "animals-list";
    }

    @GetMapping("/animals/search")
    public String  searchAnimals(@RequestParam(value = "query")String query, Model model) {
        List<Animaldto> animals = animalService.searchAnimalsByName(query);
        model.addAttribute("animals", animals);
        return "animals-list";
    }

    @GetMapping("/animals/new")
    public String newAnimal(Model model) {
        Animal animal = new Animal();
        model.addAttribute("animal", animal);
        return "animals-create";
    }

    @PostMapping("/animals/new")
    public String saveAnimal(@Valid @ModelAttribute("animal")Animaldto animaldto, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("animal", animaldto);
            return "animals-create";
        }
        animalService.saveAnimal(animaldto);
        return "redirect:/animals";
    }

    @GetMapping("/animals/{animalId}/delete")
    public String deleteAnimal(@PathVariable("animalId") Long animalId) {
        animalService.delete(animalId);
        return "redirect:/animals";
    }

    @GetMapping("animals/{animalId}/edit")
    public String editAnimal(@PathVariable("animalId") Long animalId, Model model) {
        Animaldto animal = animalService.findAnimalById(animalId);
        model.addAttribute("animal", animal);
        return "animals-edit";
    }


    @PostMapping("/animals/{animalId}/edit")
    public String updateAnimal(@PathVariable("animalId") Long animalId,
                               @Valid @ModelAttribute("animal") Animaldto animal, BindingResult result) {
        if (result.hasErrors()) {
            return "animals-edit";
        }
        animal.setId(animalId);
        animalService.updateAnimal(animal);
        return "redirect:/animals";
    }


}
