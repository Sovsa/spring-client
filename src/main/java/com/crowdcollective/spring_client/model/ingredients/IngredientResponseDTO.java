package com.crowdcollective.spring_client.model.ingredients;

public record IngredientResponseDTO(Integer id,
                                    Float amount,
                                    String unit,
                                    ProduceRequestDTO produce) {

    public IngredientResponseDTO() {
        this(0, 0f, "", new ProduceRequestDTO());
    }
}
