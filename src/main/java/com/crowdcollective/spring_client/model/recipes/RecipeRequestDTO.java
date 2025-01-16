package com.crowdcollective.spring_client.model.recipes;

import java.util.List;

import com.crowdcollective.spring_client.model.ingredients.IngredientRequestDTO;

public record RecipeRequestDTO(Integer recipeId,
                               String name,
                               String description,
                               List<InstructionRequestDTO> instructions,
                               List<IngredientRequestDTO> ingredients) {

    public RecipeRequestDTO(String name,
                            String description,
                            List<InstructionRequestDTO> instructions,
                            List<IngredientRequestDTO> ingredients) {
        this(null, name, description, instructions, ingredients);
    }
}
