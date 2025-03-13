package com.crowdcollective.spring_client.model.ingredients;

public class IngredientRequestDTO {

    private Integer id; 
    private Float amount;
    private String unit;
    private Integer produceId;

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
    public Integer getProduceId() {
        return produceId;
    }
    public void setProduceId(Integer produceId) {
        this.produceId = produceId;
    }
}
