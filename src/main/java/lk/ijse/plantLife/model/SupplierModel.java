package lk.ijse.plantLife.model;

import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PayDto;
import lk.ijse.plantLife.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
   /* public boolean SaveSupplier(SupplierDto supplierDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,supplierDto.getSupplierId());
        preparedStatement.setString(2,supplierDto.getPlantName());
        preparedStatement.setString(3,supplierDto.getPlantId());
        preparedStatement.setInt(4,supplierDto.getPlantCount());
        preparedStatement.setDouble(5,supplierDto.getPrice());

        boolean isSaved = preparedStatement.executeUpdate()>0;
        return isSaved;
    }
    public List<SupplierDto> getAllSupplier() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getDouble(5)
                    )
            );
        }
        return dtoList;
    }
    public boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE SupplierID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        return pstm.executeUpdate() > 0;
    }*/
}
