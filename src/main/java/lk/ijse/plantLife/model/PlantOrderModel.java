package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.plantOrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PlantOrderModel {

    public static boolean save(List<plantOrderDto> list) throws SQLException {
        for (plantOrderDto dto : list) {
            if (!save(dto)){
                return false;
            }
        }
        return true;
    }

    private static boolean save(plantOrderDto ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement ps = connection.prepareStatement("insert into plantorder values (?,?,?)");
        ps.setString(1,ob.getPlantId());
        ps.setString(2,ob.getOrderId());
        ps.setInt(3,ob.getQty());
        return ps.executeUpdate()>0;
    }
}
