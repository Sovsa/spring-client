package com.crowdcollective.spring_client.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.crowdcollective.spring_client.model.ingredients.IngredientRequestDTO;
import com.crowdcollective.spring_client.model.ingredients.IngredientResponseDTO;
import com.crowdcollective.spring_client.model.recipes.InstructionRequestDTO;
import com.crowdcollective.spring_client.model.recipes.InstructionResponseDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeRequestDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeResponseDTO;
import com.crowdcollective.spring_client.service.RecipeService;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private static final String INSTRUCTION_REGEXP = "instructions\\[(\\d+)\\]";
    private static final String INGREDIENT_REGEXP = "(?i)ingredients\\[(\\d+)\\]\\.(\\w+)";

    @Autowired
    private RecipeService recipeService;

    @GetMapping(path =  "/addrecipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        model.addAttribute("instructions", Collections.EMPTY_LIST);
        model.addAttribute("ingredients", Collections.EMPTY_LIST);
        return "recipes/addrecipe";
    }

    @GetMapping(path = "/addrecipe/{id}")
    public String editRecipe(@PathVariable(name = "id") Integer id, Model model) {
        RecipeDTO recipe = recipeService.getRecipe(id);
        List<IngredientResponseDTO> ingredients = recipe.getIngredients();
        List<InstructionResponseDTO> instructions = recipe.getInstructions();
        model.addAttribute("recipe", recipe);
        model.addAttribute("instructions", instructions);
        model.addAttribute("ingredients", ingredients);
        return "recipes/addrecipe";
    }

    @GetMapping
    public String listRecipes(Model model) {
        List<RecipeDTO> allRecipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", allRecipes);
        return "recipes/recipelist";
    }

    @PostMapping(path = "/addrecipe",
    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView addRecipe(@RequestParam Map<String,String> allRequestParams, 
                            Model model) {
        for(Entry<String, String> entrySet : allRequestParams.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }
        System.out.println("------------------");

        String name = allRequestParams.get("name");
        String description = allRequestParams.get("description");
        String recipeIdString = allRequestParams.get("recipeId");
        List<InstructionRequestDTO> instructions = getInstructions(allRequestParams);
        List<IngredientRequestDTO> ingredients = getIngredients(allRequestParams);
        Integer recipeId;
        RecipeRequestDTO recipe;
        String returnUrl = "addrecipe";
        if (recipeIdString != null && !recipeIdString.isEmpty()) {
            recipeId = Integer.parseInt(allRequestParams.get("recipeId"));
            recipe = new RecipeRequestDTO(recipeId, name, description, instructions, ingredients);
            returnUrl += "/" + recipeId;
        } else {
            recipe = new RecipeRequestDTO(name, description, instructions, ingredients);
        }

        RecipeResponseDTO saveOrUpdateRecipe = recipeService.saveOrUpdateRecipe(recipe);

        return new RedirectView(returnUrl);
    }

    private List<InstructionRequestDTO> getInstructions(Map<String, String> allRequestParams) {
        List<InstructionRequestDTO> instructions = new ArrayList<>();
        Pattern pattern = Pattern.compile(INSTRUCTION_REGEXP);

        for (Entry<String,String> entrySet : allRequestParams.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                Integer index = Integer.parseInt(matcher.group(1));
                instructions.add(new InstructionRequestDTO(value, index));
            }
        }

        return instructions;
    }

    private List<IngredientRequestDTO> getIngredients(Map<String, String> allRequestParams) {
        Pattern pattern = Pattern.compile(INGREDIENT_REGEXP);
        
        Map<Integer, IngredientRequestDTO> map = new HashMap<>(); 
        for (Entry<String,String> entrySet : allRequestParams.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                Integer index = Integer.parseInt(matcher.group(1));
                String variableName = matcher.group(2);
                map.computeIfAbsent(index, e -> new IngredientRequestDTO());
                
                switch (variableName) {
                    case "name":
                        map.get(index).setName(value);
                        break;
                
                    case "quantity":
                        map.get(index).setAmount(Float.parseFloat(value));
                        break;
                    
                    case "unit":
                        map.get(index).setUnit(value);
                        break;
                    
                    case "isAllergen":
                        boolean allergen = value == "on" ? true : false;
                        map.get(index).setAllergen(allergen);
                        break;
                }
            }
        }
        map.values().stream().forEach(e -> {
            System.out.println(e.getName());
        });

        return new ArrayList<>(map.values());
    }
}
