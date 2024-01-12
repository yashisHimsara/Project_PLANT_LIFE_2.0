package lk.ijse.plantLife.dao;

import lk.ijse.plantLife.dto.SupplierDto;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends SuperDAO {
    boolean SaveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;
    List<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
}
