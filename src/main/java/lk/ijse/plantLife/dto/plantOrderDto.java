package lk.ijse.plantLife.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class plantOrderDto {
    private String plantId;
    private String orderId;
    private int qty;
    private PlantDto plantDto;
}
