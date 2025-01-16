package com.crowdcollective.spring_client.model.ingredients;

public record IngredientResponseDTO(Integer id,
                                    String name,
                                    boolean allergen,
                                    Float amount,
                                    String unit) {

    public IngredientResponseDTO() {
        this(0, "", false, 0f, "");
    }

}
