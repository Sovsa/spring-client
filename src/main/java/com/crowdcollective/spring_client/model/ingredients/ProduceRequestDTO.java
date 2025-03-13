package com.crowdcollective.spring_client.model.ingredients;

import java.util.List;
import java.util.ArrayList;

public record ProduceRequestDTO(Integer id,
                                 String name,
                                 String description,
                                 boolean allergen,
                                 List<String> diets) {
    public ProduceRequestDTO() {
        this(0, "", "", false, new ArrayList());
    }
}
