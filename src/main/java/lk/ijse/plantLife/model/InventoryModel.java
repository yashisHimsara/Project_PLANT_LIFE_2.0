package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.InventoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {
    public boolean SaveInventory(InventoryDto inventoryDto) throws Exception {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql="INSERT INTO inventory VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,inventoryDto.getInventoryId());
        preparedStatement.setString(3,inventoryDto.getPlantId());
        preparedStatement.setString(2,inventoryDto.getGreenHouse());
        System.out.println("invo SAved");
        boolean isSaved=preparedStatement.executeUpdate()>0;
        return isSaved;
    }
    public List<InventoryDto> getAllInventory() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM inventory";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<InventoryDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new InventoryDto(
                            resultSet.getString(1),
                            resultSet.getString(3),
                            resultSet.getString(2)
                    )
            );
        }
        System.out.println("getinvo");
        return dtoList;
    }



}
