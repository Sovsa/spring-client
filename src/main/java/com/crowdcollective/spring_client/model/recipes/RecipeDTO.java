package com.crowdcollective.spring_client.model.recipes;

import java.util.List;

import com.crowdcollective.spring_client.model.ingredients.IngredientResponseDTO;

public class RecipeDTO {

    private Integer recipeId;
    private String name;
    private String description;
    private List<IngredientResponseDTO> ingredients;
    private List<InstructionResponseDTO> instructions;

    public Integer getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<IngredientResponseDTO> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<IngredientResponseDTO> ingredients) {
        this.ingredients = ingredients;
    }
    public List<InstructionResponseDTO> getInstructions() {
        return instructions;
    }
    public void setInstructions(List<InstructionResponseDTO> instructions) {
        this.instructions = instructions;
    }
}
