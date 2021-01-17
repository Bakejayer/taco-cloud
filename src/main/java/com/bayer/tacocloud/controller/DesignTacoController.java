package com.bayer.tacocloud.controller;

import com.bayer.tacocloud.model.Ingredient;
import com.bayer.tacocloud.model.Ingredient.Type;
import com.bayer.tacocloud.model.Order;
import com.bayer.tacocloud.model.Taco;
import com.bayer.tacocloud.repositiory.IngredientRepository;
import com.bayer.tacocloud.repositiory.TacoRepository;
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
    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo){

        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for(Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients,type));
        }
        log.info("returning design view");
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco tacoDesign, Errors errors, @ModelAttribute Order order){
        if(errors.hasErrors()){
            log.info("errors found, returning to design page");
            return "design";
        }
        //save the taco design
        log.info("Processing design: " + tacoDesign);
        Taco saved = designRepo.save(tacoDesign);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
