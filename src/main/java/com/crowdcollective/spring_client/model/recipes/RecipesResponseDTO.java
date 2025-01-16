package com.crowdcollective.spring_client.model.recipes;

import java.util.List;

import org.springframework.http.HttpStatus;

public record RecipesResponseDTO(String message,
                                HttpStatus httpStatus,
                                List<RecipeDTO> data) {
    
}
