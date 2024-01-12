package lk.ijse.plantLife.dao;

import lk.ijse.plantLife.Impl.SQLUtil;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.plantOrderDto;
import lk.ijse.plantLife.entity.Plant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlantDAO extends CrudDAO<Plant> {
     /*boolean SavePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException;
     List<PlantDto> getAllPlant() throws SQLException, ClassNotFoundException;
     boolean deletePlant(String id) throws SQLException, ClassNotFoundException;
     boolean updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException;
     boolean updateCount(String plantId, int plantCount) throws SQLException, ClassNotFoundException;
     boolean updateQty(List<plantOrderDto> list) throws SQLException;
    boolean updateQty(plantOrderDto ob) throws SQLException;*/
}
