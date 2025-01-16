package com.crowdcollective.spring_client.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import com.crowdcollective.spring_client.model.recipes.RecipeRequestDTO;
import com.crowdcollective.spring_client.model.recipes.RecipeResponseDTO;
import com.crowdcollective.spring_client.model.recipes.RecipesResponseDTO;

@Component
public class RecipeRestClient {

    private RestClient client;

    public RecipeRestClient() {
        client = RestClient.builder()
            .baseUrl("http://host.docker.internal:8081")
            .build();
    }

    public RecipeResponseDTO getRecipeWithId(Integer id) {
        return client.get()
            .uri("/recipe/" + id)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(
                httpStatusCode -> httpStatusCode.value() == HttpStatus.NOT_FOUND.value(),
                ((request, response) -> {
                    throw new ResponseStatusException(HttpStatusCode.valueOf(404),
                    "Could not find recipe with id " + id);
                })
            )
            .body(RecipeResponseDTO.class);
    }

    public RecipeResponseDTO upsertRecipe(RecipeRequestDTO recipe) {
        return client.post()
            .uri("recipe")
            .accept(MediaType.APPLICATION_JSON)
            .body(recipe)
            .retrieve()
            .body(RecipeResponseDTO.class);
    }

    public RecipesResponseDTO getAllRecipes() {
        return client.get()
            .uri("/recipe")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(RecipesResponseDTO.class);
    }
}
