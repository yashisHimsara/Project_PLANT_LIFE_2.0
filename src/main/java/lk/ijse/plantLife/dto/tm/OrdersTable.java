package lk.ijse.plantLife.dto.tm;

import java.time.LocalDate;

public class OrdersTable {
    private String orderId;
    private LocalDate orderDate;
    private String soldPlant;
    private String plantCount;
    private String customerId;
    private String plantId;
    public OrdersTable() {
    }

    public OrdersTable(String orderId, LocalDate orderDate, String soldPlant, String plantCount, String customerId, String plantId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.soldPlant = soldPlant;
        this.plantCount = plantCount;
        this.customerId = customerId;
        this.plantId = plantId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getSoldPlant() {
        return soldPlant;
    }

    public void setSoldPlant(String soldPlant) {
        this.soldPlant = soldPlant;
    }

    public String getPlantCount() {
        return plantCount;
    }

    public void setPlantCount(String plantCount) {
        this.plantCount = plantCount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }
}
