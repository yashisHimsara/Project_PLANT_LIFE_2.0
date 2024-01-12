package lk.ijse.plantLife.dto.tm;

import java.time.LocalDate;

public class PayTable {
    private String paymentId;
    private LocalDate date;
    private int price;
    private String soldPlant;

    private String plantCount;
    private String orderId;
    private String customerId;

    public PayTable() {
    }

    public PayTable(String paymentId, LocalDate date, int price, String soldPlant, String plantCount, String orderId, String customerId) {
        this.paymentId = paymentId;
        this.date = date;
        this.price = price;
        this.soldPlant = soldPlant;
        this.plantCount = plantCount;
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
