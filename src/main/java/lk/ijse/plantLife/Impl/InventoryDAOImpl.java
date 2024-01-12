package lk.ijse.plantLife.Impl;

import lk.ijse.plantLife.dao.InventoryDAO;
import lk.ijse.plantLife.dto.InventoryDto;
import lk.ijse.plantLife.entity.Customer;
import lk.ijse.plantLife.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {
    public boolean Save(InventoryDto inventoryDto) throws Exception {
        return SQLUtil.execute("INSERT INTO inventory VALUES (?,?,?)", inventoryDto.getInventoryId(), inventoryDto.getGreenHouse(),inventoryDto.getPlantId());
    }
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM inventory");
        ArrayList<InventoryDto>getAllInventory = new ArrayList<>();
        while (rst.next()){
            InventoryDto inventoryDto = new InventoryDto(rst.getString("InventoryID"), rst.getString("GreenHouse"), rst.getString("PlantID"));
            getAllInventory.add(inventoryDto);
        }
        return null;// hdnn..............
    }

    @Override
    public boolean save(Inventory dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO inventory VALUES (?,?,?)", dto.getInventoryId(), dto.getGreenHouse(),dto.getPlantId());
    }

    @Override
    public boolean update(Inventory dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return false;
    }

    @Override
    public boolean SaveInventory(InventoryDto inventoryDto) throws Exception {
        return false;
    }

    @Override
    public List<InventoryDto> getAllInventory() throws SQLException, ClassNotFoundException {
        return null;
    }
}
