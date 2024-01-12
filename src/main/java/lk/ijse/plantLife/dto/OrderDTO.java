package lk.ijse.plantLife.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String orderId;
    private Date orderDate;
    private int plantCount;
    private String customerId;
    private List<plantOrderDto> list;


}

