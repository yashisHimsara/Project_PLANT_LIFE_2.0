package lk.ijse.plantLife.Impl;

import lk.ijse.plantLife.dao.PlantDAO;
import lk.ijse.plantLife.db.DbConnection;
import lk.ijse.plantLife.dto.CustomerDto;
import lk.ijse.plantLife.dto.PlantDto;
import lk.ijse.plantLife.dto.plantOrderDto;
import lk.ijse.plantLife.entity.Plant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import lk.ijse.plantLife.Impl.SQLUtil;

public class PlantDAOImpl implements PlantDAO {
    @Override
    public  List<Plant> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM plant");
        ArrayList<Plant>getAllPlant = new ArrayList<>();
        while (rst.next()){
            Plant plant = new Plant(rst.getString("PlantID"), rst.getString("PlantName"), rst.getDouble("Price"), rst.getString("PlantAge"), rst.getString("PlantCount"));
            getAllPlant.add(plant);
        }
        return getAllPlant;
    }

    @Override
    public boolean save(Plant dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO plant (PlantID,PlantName,Price,PlantAge,PlantCount) VALUES (?,?,?,?,?)", dto.getPlantId(), dto.getPlantName(), dto.getPrice(), dto.getPlantAge(), dto.getPlantCount());

    }

    @Override
    public boolean update(Plant dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE plant SET PlantID = ?, PlantName = ?, Price = ?, PlantAge = ?, PlantCount = ? WHERE PlantID = ?", dto.getPlantId(), dto.getPlantName(), dto.getPrice(), dto.getPlantAge(), dto.getPlantCount());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM plant WHERE PlantID = ?", id);
    }
    public  boolean updateCount(String plantId, int plantCount) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE plant SET PlantCount = PlantCount - ? WHERE PlantID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, plantCount);
        pstm.setString(2, plantId);

        return pstm.executeUpdate() > 0;
    }

    public  boolean updateQty(List<plantOrderDto> list) throws SQLException {
        for (plantOrderDto ob : list) {
            if (!updateQty(ob)){
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(plantOrderDto ob) throws SQLException {
        PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(
                "update plant set PlantCount = PlantCount - ? where PlantID = ?");

        ps.setInt(1,ob.getQty());
        ps.setString(2,ob.getPlantId());

        return ps.executeUpdate()>0;

    }
}
