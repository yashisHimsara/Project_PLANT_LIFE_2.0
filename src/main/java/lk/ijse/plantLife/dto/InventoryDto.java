package lk.ijse.plantLife.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InventoryDto {

    private String inventoryId;
    private String plantId;
    private String greenHouse;

}
