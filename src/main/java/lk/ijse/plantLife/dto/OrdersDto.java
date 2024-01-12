package lk.ijse.plantLife.dto;

import java.time.LocalDate;

public class OrdersDto {

    private String orderId;
    private LocalDate orderDate;

   // private String soldPlant;
    private String plantCount;
    private String customerId;

    public OrdersDto() {

    }

    public OrdersDto(String orderId, LocalDate orderDate, String plantCount, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.plantCount = plantCount;
        this.customerId = customerId;
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
}
