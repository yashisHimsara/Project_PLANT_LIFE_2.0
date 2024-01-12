package lk.ijse.plantLife.dto.tm;

public class PlantTable {

    private String plantId;
    private String plantName;
    private Double price;
    private String plantAge;
    private String plantCount;

    public PlantTable() {
    }

    public PlantTable(String plantId, String plantName, double price, String plantAge, String plantCount) {

        this.plantId = plantId;
        this.plantName = plantName;
        this.price = price;
        this.plantAge = plantAge;
        this.plantCount = plantCount;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlantAge() {
        return plantAge;
    }

    public void setPlantAge(String plantAge) {
        this.plantAge = plantAge;
    }

    public String getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(String plantCount) {
        this.plantCount = plantCount;
    }
}
