package lk.ijse.plantLife.dto.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class InventoryTable {

    private String inventoryId;
    private String plantId;
    private String greenhouse;

}
