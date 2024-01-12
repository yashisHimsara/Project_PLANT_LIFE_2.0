package lk.ijse.plantLife.dto.tm;

import javafx.scene.control.Button;
import lk.ijse.plantLife.dto.PlantDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class CartTM {
    private String plantID;
    private String plantName;
    private double price;
    private int qty;
    private double subTotal;
    private Button action;
    private PlantDto plant;
}
