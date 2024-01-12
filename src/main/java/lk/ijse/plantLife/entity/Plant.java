package lk.ijse.plantLife.entity;

import lk.ijse.plantLife.dto.PlantDto;

public class Plant {
    private String plantId;
    private String plantName;
    private String plantCount;
    private String PlantAge;
    private double price;

    public Plant() {
    }

    public Plant(String plantId, String plantName  , double price , String plantAge , String plantCount ) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.plantCount = plantCount;
        this.PlantAge = plantAge;
        this.price = price;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(String plantCount) {
        this.plantCount = plantCount;
    }

    public String getPlantAge() {
        return PlantAge;
    }

    public void setPlantAge(String plantAge) {
        PlantAge = plantAge;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
