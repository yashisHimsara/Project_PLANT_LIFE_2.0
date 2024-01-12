package lk.ijse.plantLife.dto.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierTable {
    private String supplierId;
    private String plantName;
    private String plantId;
    private int plantCount;
    private double price;

}

