package com.bayer.tacocloud.controller;

import com.bayer.tacocloud.model.Ingredient;
import com.bayer.tacocloud.model.Ingredient.Type;
import com.bayer.tacocloud.model.Taco;

import com.bayer.tacocloud.repositiory.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type));
        }
        model.addAttribute("tacoDesign", new Taco());
        log.info("returning design view");
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("tacoDesign") Taco tacoDesign, Errors errors, Model model){
        if(errors.hasErrors()){
            log.info("errors found, returning to design page");
            return "design";
        }
        //save the taco design
        log.info("Processing design: " + tacoDesign);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
