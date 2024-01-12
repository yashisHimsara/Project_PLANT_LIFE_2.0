package lk.ijse.plantLife.Impl;

import lk.ijse.plantLife.dao.SupplierDAO;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean SaveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)", supplierDto.getSupplierId(), supplierDto.getPlantName(), supplierDto.getPlantId(), supplierDto.getPlantCount(), supplierDto.getPrice());
    }
    public List<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<SupplierDto>getAllSupplier = new ArrayList<>();
        while (rst.next()){
            SupplierDto supplierDto = new SupplierDto(rst.getString("SupplierID"), rst.getString("PlantName"), rst.getString("PlantID"), rst.getInt("PlantCount"), rst.getDouble("Price"));
            getAllSupplier.add(supplierDto);
        }
        return getAllSupplier;
    }
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE SupplierID = ?", id);
    }
}
