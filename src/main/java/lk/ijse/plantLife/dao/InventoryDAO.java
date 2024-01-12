package lk.ijse.plantLife.dao;

import lk.ijse.plantLife.Impl.SQLUtil;
import lk.ijse.plantLife.dto.InventoryDto;
import lk.ijse.plantLife.entity.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory> {
     boolean SaveInventory(InventoryDto inventoryDto) throws Exception;
     List<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException;
}
