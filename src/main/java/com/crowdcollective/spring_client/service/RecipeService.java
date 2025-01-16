package com.crowdcollective.spring_client.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdcollective.spring_client.model.ingredients.IngredientResponseDTO;
import com.crowdcollective.spring_client.model.recipes.InstructionResponseDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeRequestDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeResponseDTO;

@Service
public class RecipeService {

    @Autowired
    private RecipeRestClient recipeRestClient;

    public RecipeDTO getRecipe(Integer recipeId) {
        RecipeDTO result = recipeRestClient.getRecipeWithId(recipeId).data();
        sortRecipe(result);
        
        return result;
    }

    public RecipeResponseDTO saveOrUpdateRecipe(RecipeRequestDTO recipe) {
        return recipeRestClient.upsertRecipe(recipe);
    }

    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> result = recipeRestClient.getAllRecipes().data();
        for (RecipeDTO recipe : result) {
            sortRecipe(recipe);
        }
        return result;
    }

    public void sortRecipe(RecipeDTO recipe) {
        recipe.setIngredients(recipe.getIngredients().stream().sorted(Comparator.comparing(IngredientResponseDTO::id)).collect(Collectors.toList()));
        recipe.setInstructions(recipe.getInstructions().stream().sorted(Comparator.comparing(InstructionResponseDTO::index)).collect(Collectors.toList()));
    }
}
