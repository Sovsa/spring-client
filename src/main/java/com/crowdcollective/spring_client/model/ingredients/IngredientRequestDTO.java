package com.crowdcollective.spring_client.model.ingredients;

public class IngredientRequestDTO {

    private Integer id;
    private String name;
    private boolean allergen;
    private Float amount;
    private String unit;

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isAllergen() {
        return allergen;
    }
    public void setAllergen(boolean allergen) {
        this.allergen = allergen;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }    
}
