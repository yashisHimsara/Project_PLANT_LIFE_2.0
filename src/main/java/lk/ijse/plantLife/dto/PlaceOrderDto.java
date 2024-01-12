package lk.ijse.plantLife.dto;

import lk.ijse.plantLife.dto.tm.PlantTable;


import java.util.ArrayList;
import java.util.List;


public class PlaceOrderDto {

   private String orderId;
   private String orderDate;
   private String soldPlant;
   private String plantCount;
    private List<PlantTable> cartTmList = new ArrayList<>();

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(String orderId, String orderDate, String soldPlant, String plantCount, List<PlantTable> cartTmList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.soldPlant = soldPlant;
        this.plantCount = plantCount;
        this.cartTmList = cartTmList;
    }

    public List<PlantTable> getCartTmList() {
        return cartTmList;
    }

    public void setCartTmList(List<PlantTable> cartTmList) {
        this.cartTmList = cartTmList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
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
}
