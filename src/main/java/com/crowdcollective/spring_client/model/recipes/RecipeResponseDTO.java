package com.crowdcollective.spring_client.model.recipes;

import org.springframework.http.HttpStatus;

public record RecipeResponseDTO(String message,
                                HttpStatus httpStatus,
                                RecipeDTO data) {
    
}
