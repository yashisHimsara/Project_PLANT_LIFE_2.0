package lk.ijse.plantLife.bo.Impl;

import lk.ijse.plantLife.dao.DAOFactory;
import lk.ijse.plantLife.dao.InventoryDAO;
import lk.ijse.plantLife.dto.InventoryDto;
import lk.ijse.plantLife.entity.Inventory;

import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryBOImpl {

    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INVENTORY);

    public ArrayList<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        ArrayList<Inventory> inventories = inventoryDAO.getAll();
        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();
        for (Inventory inventory : inventories){
            inventoryDtos.add(new InventoryDto(inventory.getInventoryId(), inventory.getGreenHouse(), inventory.getPlantId()));
        }
        return inventoryDtos;
    }
    public boolean saveInventory(InventoryDto dto) throws SQLException, ClassNotFoundException {
        return inventoryDAO.save(new Inventory(dto.getInventoryId(), dto.getGreenHouse(), dto.getPlantId()));
    }
}
