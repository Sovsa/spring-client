package com.crowdcollective.spring_client.model.recipes;

public record InstructionResponseDTO(Integer id,
                                     String instructionText,
                                     Integer index) {

    public InstructionResponseDTO() {
        this(0, "", 0);
    }
}
