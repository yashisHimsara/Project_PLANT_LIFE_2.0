package lk.ijse.plantLife.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDto {

    private String supplierId;
    private String plantName;
    private String plantId;
    private int plantCount;
    private double price;

}
